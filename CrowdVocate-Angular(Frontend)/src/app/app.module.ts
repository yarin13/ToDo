import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { MainComponent } from './main/main.component';
import { NewTaskComponent } from './new-task/new-task.component';

import { FormsModule,ReactiveFormsModule } from '@angular/forms'
import {HttpClientModule} from '@angular/common/http';
import {RouterModule,Routes} from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material/material.module';
import { DeleteDialogComponent } from './delete-dialog/delete-dialog.component';
import { EditTaskComponent } from './edit-task/edit-task.component'
import { ScrollingModule } from '@angular/cdk/scrolling';
const appRoutes:Routes = [
  { path: 'home', component: MainComponent },
  { path: 'createTask', component: NewTaskComponent },
  { path: '**', component: MainComponent }
]



@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    MainComponent,
    NewTaskComponent,
    DeleteDialogComponent,
    EditTaskComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MaterialModule,
    ScrollingModule
  ],
  entryComponents: [
    DeleteDialogComponent,
    NewTaskComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
