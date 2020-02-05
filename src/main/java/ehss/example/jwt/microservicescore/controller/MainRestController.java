package ehss.example.jwt.microservicescore.controller;

import ehss.example.jwt.microservicescore.data.*;
import ehss.example.jwt.microservicescore.service.ResourceService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(value="Integration micro service", description="Operations pertaining to Integration micro services API")
public class MainRestController {

    ResourceService resourceService;

    @Autowired
    public MainRestController(ResourceService resourceService){
        this.resourceService = resourceService;
    }

    @GetMapping("/resources")
    @ApiOperation(value = "View a list of available resources", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list",response = List.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource",response = ErrorDetails.class),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden",response = ErrorDetails.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found",response = ErrorDetails.class)
    })
    public List<GenericKPData> getAllResources(@ApiParam(value = "Auth header", required = true) @RequestHeader("Authorization") String authorization) {
        return resourceService.getResources();
    }

    @GetMapping("/resources/{id}")
    @ApiOperation(value = "View a resource", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list",response = GenericKPData.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource",response = ErrorDetails.class),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden",response = ErrorDetails.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found",response = ErrorDetails.class)
    })
    public GenericKPData read(@ApiParam(value = "Id of the resource to get", required = true) @PathVariable String id,
                              @ApiParam(value = "Auth header", required = true) @RequestHeader("Authorization") String authorization)
            throws InvalidRequestException, ServiceException, ResourceNotFoundException {
        return resourceService.getResources(id);
    }


    @ApiOperation(value = "Add a resource")
    @PostMapping("/resources")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully added a resource",response = GenericKPData.class),
            @ApiResponse(code = 400, message = "Bad request something is missing",response = ErrorDetails.class),
            @ApiResponse(code = 401, message = "You are not authorized to create the resource",response = ErrorDetails.class),
            @ApiResponse(code = 500, message = "Internal Service error",response = ErrorDetails.class)
    })
    public ResponseEntity<GenericKPData> create(
            @ApiParam(value = "Resource object stored ", required = true) @Valid @RequestBody GenericKPData genericKPData,
            @ApiParam(value = "Auth header", required = true) @RequestHeader("Authorization") String authorization)

            throws InvalidRequestException, ServiceException {
        return new ResponseEntity<>(resourceService.createResource(genericKPData),HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a resource")
    @PutMapping ("/resources")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated a resource",response = GenericKPData.class),
            @ApiResponse(code = 400, message = "Bad request something is missing",response = ErrorDetails.class),
            @ApiResponse(code = 401, message = "You are not authorized to update the resource",response = ErrorDetails.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found",response = ErrorDetails.class),
            @ApiResponse(code = 500, message = "Internal Service error",response = ErrorDetails.class)
    })
    public ResponseEntity<GenericKPData> update(
            @ApiParam(value = "Resource object stored ", required = true) @Valid @RequestBody GenericKPData genericKPData,
            @ApiParam(value = "Auth header", required = true) @RequestHeader("Authorization") String authorization)

            throws InvalidRequestException, ServiceException {
        return new ResponseEntity<>(resourceService.createResource(genericKPData),HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a resource")
    @DeleteMapping ("/resources/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted a resource."),
            @ApiResponse(code = 400, message = "Bad request something is missing.",response = ErrorDetails.class),
            @ApiResponse(code = 401, message = "You are not authorized to update the resource.",response = ErrorDetails.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found.",response = ErrorDetails.class),
            @ApiResponse(code = 403, message = "Resource cannot be deleted.",response = ErrorDetails.class),
            @ApiResponse(code = 500, message = "Internal Service error",response = ErrorDetails.class)
    })
    public ResponseEntity<String> delete(@ApiParam(value = "Id of the resource to delete", required = true) @PathVariable String id,
                                         @ApiParam(value = "Auth header", required = true) @RequestHeader("Authorization") String authorization)

            throws InvalidRequestException, ServiceException,ResourceNotFoundException {
        return new ResponseEntity<>("Successfully deleted a resource.", HttpStatus.NO_CONTENT);
    }


}
