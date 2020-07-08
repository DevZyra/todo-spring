package pl.devzyra.todospring.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.devzyra.todospring.model.Project;
import pl.devzyra.todospring.model.ProjectStep;
import pl.devzyra.todospring.model.projection.ProjectWriteModel;
import pl.devzyra.todospring.services.ProjectService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    @ModelAttribute("projects")
    List<Project> getProjects(){
        return projectService.findAll();
    }

    @GetMapping
    String showProjects(Model model){
        model.addAttribute("project",new ProjectWriteModel());
        return "projects";
    }

    @PostMapping
    String addProject(
            @ModelAttribute("project") @Valid ProjectWriteModel current, BindingResult bindingResult,
            Model model){
        if(bindingResult.hasErrors()){
            return "projects";
        }

        projectService.save(current);
        model.addAttribute("project", new ProjectWriteModel());
        model.addAttribute("projects", getProjects());
        model.addAttribute("message","Projekt dodany");
        return "projects";
    }

    @PostMapping(params = "addStep")
    String addProjectStep(@ModelAttribute("project") ProjectWriteModel current){
        current.getSteps().add(new ProjectStep());
        return "projects";
    }

    @PostMapping("{id}")
    String createGroup(
            @ModelAttribute("project") ProjectWriteModel current,
            Model model,
            @PathVariable Long id,
            @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime deadline){

        try {
            projectService.createGroup(deadline, id);
            model.addAttribute("message","Dodano grupę");
        }catch (IllegalStateException | IllegalArgumentException e){
            model.addAttribute("message","Błąd podczas tworzenia grupy");
        }
        return "projects";
    }

}
