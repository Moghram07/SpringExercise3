package com.example.day3ex.Controller;

import com.example.day3ex.Api.ApiResponse;
import org.springframework.web.bind.annotation.*;
import com.example.day3ex.Model.Task;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    ArrayList<Task> tasks = new ArrayList<>();

    @GetMapping("/get")
    public java.util.ArrayList<Task> getTasks(){
        return tasks;
    }
    @PostMapping("/add")
    public ApiResponse addTask(@RequestBody Task task){
        tasks.add(task);
        return new ApiResponse("task added", "200");
    }
    @PutMapping("/update/{index}")
    public ApiResponse updateTask(@PathVariable int index, @RequestBody Task task){
        tasks.set(index, task);
        return new ApiResponse("task updated", "200");
    }
    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTask(@PathVariable int index){
        tasks.remove(index);
        return new ApiResponse("task deleted", "200");
    }
    @PutMapping("/change/{index}")
    public ApiResponse changeTaskStatus(@PathVariable int index){
       if(tasks.get(index).getStatus().equals("done")){
           tasks.get(index).setStatus(tasks.get(index).getStatus().equals("done")?"not done":"done");
       }else{
           tasks.get(index).setStatus(tasks.get(index).getStatus().equals("not done")?"done":"not done");
       }
       return new ApiResponse("task updated", "200");
    }
    @GetMapping("/search/{name}")
    public ApiResponse searchTask(@PathVariable String name){
        for(Task task : tasks){
            if(task.getTitle().equals(name)){
               return new ApiResponse( "task found" + task.toString(),"200");
            }
        }
       return new ApiResponse("task not found", "404");
    }

}
