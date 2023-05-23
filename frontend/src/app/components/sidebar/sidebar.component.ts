import { Component } from '@angular/core';
import { HomeComponent } from '../home/home.component';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {

  constructor(private homeComponent : HomeComponent) {}

  loadUser() {
    this.homeComponent.fetchUserByUsername();
  }

}
