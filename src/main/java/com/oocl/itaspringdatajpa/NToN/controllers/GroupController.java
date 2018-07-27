package com.oocl.itaspringdatajpa.NToN.controllers;

import com.oocl.itaspringdatajpa.NToN.controllers.dto.GroupDTO;
import com.oocl.itaspringdatajpa.NToN.entities.Group;
import com.oocl.itaspringdatajpa.NToN.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Vito Zhuang on 7/27/2018.
 */
@RestController
@RequestMapping("/groups")
public class GroupController {
	@Autowired
	GroupRepository groupRepository;

	@GetMapping(path = "")
	public List<GroupDTO> findAllGroups(){
		return groupRepository.findAll()
				.stream()
				.map(group -> new GroupDTO(group))
				.collect(Collectors.toList());
	}

	@PostMapping(path = "")
	public GroupDTO addGroup(@RequestBody Group group){
		return new GroupDTO(groupRepository.save(group));
	}

	@GetMapping(path = "/{id}")
	public GroupDTO findGroupById(@PathVariable Long id){
		return new GroupDTO(groupRepository.findById(id).get());
	}
}
