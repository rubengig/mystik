import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UsuarioService } from '../../../service/usuario.service';
import { IUsuario } from '../../../model/usuario.interface';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-usuario.view.routed',
  templateUrl: './usuario.view.routed.component.html',
  styleUrls: ['./usuario.view.routed.component.css']
})
export class UsuarioViewRoutedComponent implements OnInit {
  
  // Cambiamos id por email
  email: string = '';
  route: string = '';
  oUsuario: IUsuario = {} as IUsuario;
  numeroPedidos: number = 0;
  
  constructor(private oActivatedRoute: ActivatedRoute, private oUsuarioService: UsuarioService) { }

  ngOnInit() {
    // Recogemos el parámetro 'email' de la URL en lugar del 'id'
    this.email = this.oActivatedRoute.snapshot.params['email'];

    // Llamada al nuevo método
    this.getByEmail();
  }

  getByEmail() {
    // Usamos el método del servicio que busca por email
    this.oUsuarioService.getUsuarioByEmail(this.email).subscribe({
      next: (data: IUsuario) => {
        this.oUsuario = data;
      },
      error: (error: HttpErrorResponse) => {
        console.error("Error al recuperar el usuario:", error);
      }
    });
  }
}