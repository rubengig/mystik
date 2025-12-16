import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { IArticulo } from './../../../model/articulo.interface';
import { ArticuloService } from '../../../service/articulo.service';
import { ComentarioPlistUnroutedComponent } from "../../comentario/comentario.plist.unrouted/comentario.plist.unrouted.component";

@Component({
  selector: 'app-articulo.user.view.routed',
  templateUrl: './articulo.user.view.routed.component.html',
  styleUrls: ['./articulo.user.view.routed.component.css'],
  imports: [ComentarioPlistUnroutedComponent],
  standalone: true,
})
export class ArticuloUserViewRoutedComponent implements OnInit {
  
  id: number = 0;
  route: string = '';
  oArticulo: IArticulo = {} as IArticulo;
  numeroPedidos: number = 0;
  //
  constructor(private oActivatedRoute: ActivatedRoute, private oArticuloService: ArticuloService) {
    

    
   }

  ngOnInit() {
    this.id = this.oActivatedRoute.snapshot.params['id'];

    //llamada
    this.getOne();
  }

  // --- FUNCIONES PARA GENERAR DATOS MÍSTICOS BASADOS EN EL ID ---

getOrigen(id: number): { texto: string, icono: string } {
  const origenes = [
    { texto: 'El Velo', icono: 'blur_on' },
    { texto: 'Aqueronte', icono: 'waves' },
    { texto: 'Caelum', icono: 'cloud' },
    { texto: 'La Forja', icono: 'whatshot' },
    { texto: 'Bosque Antiguo', icono: 'forest' },
    { texto: 'Ruinas', icono: 'temple_buddhist' },
    { texto: 'Abismo', icono: 'visibility_off' }
  ];
  // El operador % asegura que siempre elija uno de la lista cíclicamente
  return origenes[id % origenes.length];
}

getAura(id: number): { texto: string, icono: string } {
  const auras = [
    { texto: 'Radiante', icono: 'flare' },
    { texto: 'Corrupta', icono: 'pest_control_rodent' }, // o algo siniestro
    { texto: 'Etérea', icono: 'air' },
    { texto: 'Inestable', icono: 'electric_bolt' },
    { texto: 'Serena', icono: 'water_drop' },
    { texto: 'Maldita', icono: 'skull' }
  ];
  return auras[id % auras.length];
}

getClase(id: number): { texto: string, icono: string } {
  const clases = [
    { texto: 'Reliquia', icono: 'diamond' },
    { texto: 'Catalizador', icono: 'all_inclusive' },
    { texto: 'Consumible', icono: 'liquor' }, // pociones
    { texto: 'Escritura', icono: 'menu_book' },
    { texto: 'Tótem', icono: 'pets' },
    { texto: 'Amuleto', icono: 'workspace_premium' }
  ];
  return clases[id % clases.length];
}

  getOne() {
      this.oArticuloService.getOne(this.id).subscribe({
        next: (data: IArticulo) => {
          this.oArticulo = data;
        },
      });
    }

}
