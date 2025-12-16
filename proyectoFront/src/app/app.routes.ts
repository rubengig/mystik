import { Routes } from '@angular/router';
import { SharedHomeRoutedComponent } from './components/shared/shared.home.routed/shared.home.routed.component';
import { UsuarioAdminPlistRoutedComponent } from './components/usuario/usuario.admin.plist.routed/usuario.admin.plist.routed.component';
import { UsuarioAdminViewRoutedComponent } from './components/usuario/usuario.admin.view.routed/usuario.admin.view.routed.component';
import { UsuarioAdminEditRoutedComponent } from './components/usuario/usuario.admin.edit.routed/usuario.admin.edit.routed.component';
import { UsuarioAdminDeleteRoutedComponent } from './components/usuario/usuario.admin.delete.routed/usuario.admin.delete.routed.component';
import { UsuarioAdminCreateRoutedComponent } from './components/usuario/usuario.admin.create.routed/usuario.admin.create.routed.component';
import { ArticuloAdminPlistRoutedComponent } from './components/articulo/articulo.admin.plist.routed/articulo.admin.plist.routed.component';
import { ArticuloAdminViewRoutedComponent } from './components/articulo/articulo.admin.view.routed/articulo.admin.view.routed.component';
import { ArticuloAdminDeleteRoutedComponent } from './components/articulo/articulo.admin.delete.routed/articulo.admin.delete.routed.component';
import { ArticuloAdminEditRoutedComponent } from './components/articulo/articulo.admin.edit.routed/articulo.admin.edit.routed.component';
import { ArticuloUserPlistRoutedComponent } from './components/articulo/articulo.user.plist.routed/articulo.user.plist.routed.component';
import { ArticuloUserViewRoutedComponent } from './components/articulo/articulo.user.view.routed/articulo.user.view.routed.component';
import { SharedLogoutRoutedComponent } from './components/shared/shared.logout.routed/shared.logout.routed.component';
import { SharedLoginRoutedComponent } from './components/shared/shared.login.routed/shared.login.routed.component';
import { AdminGuard } from './guards/admin.guards';
import { UserGuard } from './guards/user.guards';
import { UsuarioViewRoutedComponent } from './components/usuario/usuario.view.routed/usuario.view.routed.component';


export const routes: Routes = [

    { path: '', component: SharedHomeRoutedComponent },
    { path: 'home', component: SharedHomeRoutedComponent },

    { path: 'admin/usuario/plist', component: UsuarioAdminPlistRoutedComponent, canActivate: [AdminGuard] },
    { path: 'admin/usuario/view/:id', component: UsuarioAdminViewRoutedComponent, canActivate: [AdminGuard] },
    { path: 'admin/usuario/edit/:id', component: UsuarioAdminEditRoutedComponent, canActivate: [AdminGuard] },
    { path: 'admin/usuario/delete/:id', component: UsuarioAdminDeleteRoutedComponent, canActivate: [AdminGuard, UserGuard] },
    { path: 'admin/usuario/create', component: UsuarioAdminCreateRoutedComponent, pathMatch: 'full', canActivate: [AdminGuard] },

    { path: 'admin/articulo/plist', component: ArticuloAdminPlistRoutedComponent, canActivate: [AdminGuard] },
    { path: 'admin/articulo/view/:id', component: ArticuloAdminViewRoutedComponent, canActivate: [AdminGuard] },
    { path: 'admin/articulo/delete/:id', component: ArticuloAdminDeleteRoutedComponent, canActivate: [AdminGuard] },
    { path: 'admin/articulo/edit/:id', component: ArticuloAdminEditRoutedComponent, canActivate: [AdminGuard] },
    
    { path: 'user/usuario/view/:email', component: UsuarioViewRoutedComponent },
    { path: 'user/articulo/plist', component: ArticuloUserPlistRoutedComponent },
    { path: 'user/articulo/view/:id', component: ArticuloUserViewRoutedComponent },

    { path: 'login', component: SharedLoginRoutedComponent },
    { path: 'logout', component: SharedLogoutRoutedComponent },

];
