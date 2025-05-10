package com.tinnt.user_service.controller;

import com.netflix.discovery.converters.Auto;
import com.tinnt.user_service.assembler.UserModelAssembler;
import com.tinnt.user_service.client.ValidateClient;
import com.tinnt.user_service.dto.request.UserRequestBodyDTO;
import com.tinnt.user_service.dto.request.UserRequestParamDto;
import com.tinnt.user_service.dto.response.APIResponseBody;
import com.tinnt.user_service.dto.response.UserResponseDTO;
import com.tinnt.user_service.entity.UserEntity;
import com.tinnt.user_service.mapper.UserMapper;
import com.tinnt.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	UserModelAssembler assembler;

	@Autowired
	PagedResourcesAssembler<UserEntity> pagedAssembler;

	@Autowired
	ValidateClient validate;

	@PostMapping("")
	public ResponseEntity<APIResponseBody<UserResponseDTO>> createUser(@RequestBody UserRequestBodyDTO dto) {
		List<String> errors = validate.validateObject("user", dto);
		APIResponseBody result = new APIResponseBody();
		if(errors.size() > 0) {
			result.setData(null);
			result.setErrorCode(HttpStatus.BAD_REQUEST);
			result.setMessage(errors.toString());
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		UserResponseDTO user = UserMapper.toDTO(userService.createUser(dto));

		result.setData(user);
		result.setErrorCode(HttpStatus.CREATED);
		result.setMessage(null);
		return new ResponseEntity<>(result, HttpStatus.CREATED);

	}

	@GetMapping("")
	public CollectionModel<UserResponseDTO> getAll() {
		List<UserEntity> listEntity = userService.getAllUser();
		List<UserResponseDTO> listDto = UserMapper.toListDto(listEntity);

		CollectionModel<UserResponseDTO> resource = CollectionModel.of(listDto);
		resource.add(linkTo(methodOn(UserController.class).getAll()).withSelfRel());

		return resource;
	}

	@GetMapping("/list")
	public ResponseEntity<PagedModel<EntityModel<UserResponseDTO>>>  getAllWithPaging(@ModelAttribute UserRequestParamDto params, @PageableDefault(size = 5) Pageable pageable) {
		Page<UserEntity> users = userService.findUsers(params, pageable);
		PagedModel<EntityModel<UserResponseDTO>> pagedModel = pagedAssembler.toModel(users, assembler);
		return new ResponseEntity<>(pagedModel, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public EntityModel<UserResponseDTO> getDetail(@PathVariable("id") String userId) {
		UserResponseDTO dto = userService.getDetailluser(userId);
		EntityModel<UserResponseDTO> resource = EntityModel.of(dto);

		// Add relate link ro resource
		resource.add(linkTo(methodOn(UserController.class).getDetail(userId)).withSelfRel());
		resource.add(linkTo(methodOn(UserController.class).getAll()).withRel("get-all-users"));

//		// Add Task-Service endpoint to _link
//		List<ServiceInstance> listInstances = discoveryClient.getInstances("task-service"); // Get all instances of task-service
//		if(!listInstances.isEmpty()) {
//			// Get task-service endpoint from instances
//			String serviceUrl = listInstances.get(0).getUri().toString();
//			String linkService = serviceUrl + "/api/tasks?username=" + dto.getUsername();
//
//			// add endpoint to resource
//			resource.add(Link.of(linkService, "get-task-by-username"));
//		}

		return resource;
	}

	@PatchMapping("/{id}")
	public ResponseEntity<UserResponseDTO> update(@PathVariable("id") String userId, @RequestBody UserRequestBodyDTO dto) throws Exception {
		UserEntity entity = userService.updateUser(dto, userId);
		UserResponseDTO result = UserMapper.toDTO(entity);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}


}
