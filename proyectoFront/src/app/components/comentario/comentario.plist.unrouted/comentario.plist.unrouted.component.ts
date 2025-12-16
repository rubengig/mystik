import { Component, Input, OnInit, SimpleChanges } from '@angular/core';
import { ComentarioService } from '../../../service/comentario.service';
import { UsuarioService } from '../../../service/usuario.service';
import { SessionService } from '../../../service/session.service';
import { IComentario } from '../../../model/comentario.interface';
import { IPage } from '../../../model/model.interface';
import { IUsuario } from '../../../model/usuario.interface';
import { BotoneraService } from '../../../service/botonera.service';
import { FormsModule } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-comentario-plist-unrouted',
  templateUrl: './comentario.plist.unrouted.component.html',
  styleUrls: ['./comentario.plist.unrouted.component.css'],
  standalone: true,
  imports: [FormsModule]
})
export class ComentarioPlistUnroutedComponent implements OnInit {

  @Input() id_articulo!: number;

  arrBotonera: string[] = [];
  oPage: IPage<IComentario> | null = null;
  nPage: number = 0;
  nRpp: number = 10;
  nuevoComentario: string = '';

  constructor(
    private oComentarioService: ComentarioService,
    private oBotoneraService: BotoneraService,
    private oSessionService: SessionService,
    private oUsuarioService: UsuarioService,
    private oRouter: Router
  ) { }

  ngOnInit() {
    // La carga inicial
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['id_articulo'] && changes['id_articulo'].currentValue) {
      this.getPage();
    }
  }

  getPage() {
    this.oComentarioService
      .getPageXArticulo(this.nPage, this.nRpp, 'id', 'desc', '', this.id_articulo)
      .subscribe({
        next: (oPageFromServer: IPage<IComentario>) => {
          this.oPage = oPageFromServer;
          this.arrBotonera = this.oBotoneraService.getBotonera(
            this.nPage,
            oPageFromServer.totalPages
          );
        },
        error: (err) => {
          console.log(err);
        }
      });
  }

 addComentario() {
    // 1. Validaciones básicas
    if (!this.nuevoComentario.trim()) return;

    if (!this.oSessionService.isSessionActive()) {
      this.oRouter.navigate(['/login']);
      return;
    }

    const strEmail = this.oSessionService.getSessionEmail();

    // 2. Buscamos el usuario
    this.oUsuarioService.getUsuarioByEmail(strEmail).subscribe({
      next: (oUsuarioReal: IUsuario) => {
        
        // 3. CREAMOS EL PAYLOAD LIMPIO (SIN REFERENCIAS CIRCULARES)
        // Usamos 'any' para saltarnos la comprobación estricta de TypeScript temporalmente
        // y asegurarnos de enviar SOLO lo que Java necesita para vincular (los IDs).
        const payload: any = {
          id: 0,
          texto: this.nuevoComentario,
          articulo: { 
            id: this.id_articulo 
          },
          usuario: { 
            id: oUsuarioReal.id 
          }
        };

        // DEBUG: Mira la consola del navegador (F12) antes de que falle.
        // Debe salir: { id: 0, texto: "...", articulo: {id: X}, usuario: {id: Y} }
        // Si ves "tipousuario" aquí, es que algo está mal.
        console.log("Enviando al backend:", JSON.stringify(payload));

        // 4. Enviar
        this.oComentarioService.create(payload).subscribe({
          next: (res) => {
            this.nuevoComentario = '';
            this.getPage();
          },
          error: (err: HttpErrorResponse) => {
            console.error('Error del servidor:', err);
          }
        });

      },
      error: (err) => {
        console.error("Error al obtener usuario:", err);
      }
    });
  }
}