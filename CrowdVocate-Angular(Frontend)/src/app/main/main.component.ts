import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DeleteDialogComponent } from '../delete-dialog/delete-dialog.component';
import { NewTaskComponent } from '../new-task/new-task.component';
import { Task } from '../shared/models/task.model';
import {TasksService} from '../shared/services/tasks.service'
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import { MatTableDataSource } from'@angular/material/table'
import {MatSort} from '@angular/material/sort';


@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
 
  _data: MatTableDataSource<Task>;

  constructor(private myTasks:TasksService,public dialog:MatDialog) { 
        this._data = new MatTableDataSource([]);
        this.myTasks.getTasksPagin(this._data);
     
  }
  ngOnInit(): void {

  }

  ngAfterViewInit() {
    this._data.paginator = this.paginator;
    this._data.sort = this.sort;
    }

  displayedColumns: string[] = ['name', 'description', 'status', 'startDate','endDate','actions'];


  refresh() {
    this.myTasks.update().subscribe(res => {
      this._data.data = res;
    });
  }

  public deleteButton(id:number){
    let dialogRef = this.dialog.open(DeleteDialogComponent);
    dialogRef.afterClosed().subscribe(result=>{
      if(result == "true"){
        this.myTasks.deleteTask(id).subscribe(()=>{
          this.refresh();
        });  
      }
    }) 
  }
 

  public createTask(){
    let dialogRef = this.dialog.open(NewTaskComponent);
    dialogRef.afterClosed().subscribe(result=>{
      if(result == "apply")
        this.refresh();
    })
  }

  public editTask(editTask:Task){
    console.log(editTask);
    let dialogRef = this.dialog.open(NewTaskComponent,{data:{task:editTask, flag:true}});
    dialogRef.afterClosed().subscribe(result=>{
      if(result == "apply")
        this.refresh();
    })

  }


  public applyFilter(filterValue:string){
    this._data.filter = filterValue.trim().toLowerCase();
  }


  public get tasks(){
    if(this.myTasks.myAllTasks)
       return this.myTasks.myAllTasks;
     return null;
   }

}
