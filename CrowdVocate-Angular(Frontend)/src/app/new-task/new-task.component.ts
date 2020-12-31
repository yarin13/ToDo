import { Component, OnInit,Inject } from '@angular/core';
import { Task } from '../shared/models/task.model';
import {TasksService} from '../shared/services/tasks.service'




import {MAT_DIALOG_DATA} from '@angular/material/dialog';

@Component({
  selector: 'app-new-task',
  templateUrl: './new-task.component.html',
  styleUrls: ['./new-task.component.css']
})
export class NewTaskComponent implements OnInit {



  constructor(private myTasks:TasksService, @Inject(MAT_DIALOG_DATA) public data: {task:any,flag:any}) { 

    if(data != undefined)
    {
        this.newTask.id = data.task.id;
        this.newTask.name = data.task.name;
        this.newTask.description = data.task.description;
        this.newTask.status = data.task.status;
        this.newTask.startDate = data.task.startDate;
        this.newTask.endDate = data.task.endDate;
        this.flag = true;
    }
    console.log("flag: "+this.flag);
  }


  public statusOptions:string[] = ["To do","In progress","Done"];

  public flag:boolean = false;

  public newTask:Task = {
    name:"",
    description:"",
    status:"To do",
    startDate:"",
    endDate:"",
  };

  ngOnInit(): void {
  }



  public apply(){
    if(this.newTask.name.length == 0 || this.newTask.description.length == 0 || this.newTask.status.length == 0 || this.newTask.startDate.length == 0)
      alert("Please fill all fields!");  
    else{
      if(this.flag)
        this.myTasks.updateTask(this.newTask);
      else
        this.myTasks.createTask("create",this.newTask);
      
    }
      
  }

}
