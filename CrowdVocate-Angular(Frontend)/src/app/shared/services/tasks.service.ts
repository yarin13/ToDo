import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Task } from '../models/task.model'
import {allTasksModel} from '../models/root-tasks.model'
import {MatTableDataSource} from'@angular/material/table';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class TasksService {

  constructor(private myHttpClient: HttpClient) { 
    this.getTasks();
}




// public _myAllTasks:allTasksModel = new allTasksModel;
public _myAllTasks:Task[] = [];

public get myAllTasks(){
  return this._myAllTasks;
}

public set myAllTasks(tasks:Task[]){
  this._myAllTasks = tasks;
}


public  getTasks() {
  this.myHttpClient
      .get<Task[]>("http://localhost:8080/CrowdVocate/TaskServlet")
      .subscribe(
          res => {
            this.myAllTasks = res;
          },
          err=>{
            console.log(err);
          }
      )
}

public  getTasksPagin( data: MatTableDataSource<Task>) {
  this.myHttpClient
      .get<Task[]>("http://localhost:8080/CrowdVocate/TaskServlet")
      .subscribe(
          res => {
            data.data = res;
          },
          err=>{
            console.log(err);
          }
      )
}

public update():Observable<Task[]>{
  return  this.myHttpClient
  .get<Task[]>("http://localhost:8080/CrowdVocate/TaskServlet");
}

public deleteTask(id:number){ 
  return this.myHttpClient
  .delete("http://localhost:8080/CrowdVocate/TaskServlet?id="+id);
}
// public deleteTask(id:number){ 
//   this.myHttpClient
//   .delete("http://localhost:8080/CrowdVocate/TaskServlet?id="+id)
//   .subscribe(
//       res => {
//         console.log(res)
//         this.getTasks();
//       },
//       err=>{console.log(err)}
//   )
// }



public createTask(action:string,newTask:Task){
  this.myHttpClient
  .post("http://localhost:8080/CrowdVocate/TaskServlet?action="+action,newTask)
  .subscribe(
    res => {
      console.log(res)
      this.getTasks();
    },
    err=>{console.log(err)}
  )
  
}


public updateTask(newTask:Task){
  console.log("updating");
  this.myHttpClient
  .put("http://localhost:8080/CrowdVocate/TaskServlet",newTask)
  .subscribe(
    res => {
      console.log(res)
      this.getTasks();
    },
    err=>{console.log(err)}
  )
}

}
