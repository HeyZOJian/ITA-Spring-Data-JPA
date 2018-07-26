package com.oocl.itaspringdatajpa.OneToOne.controllers;

import com.oocl.itaspringdatajpa.OneToOne.controllers.dto.KlassDTO;
import com.oocl.itaspringdatajpa.OneToOne.entities.Klass;
import com.oocl.itaspringdatajpa.OneToOne.entities.Leader;
import com.oocl.itaspringdatajpa.OneToOne.repositories.LeaderRepository;
import com.oocl.itaspringdatajpa.OneToOne.repositories.KlassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Vito Zhuang on 7/26/2018.
 */
@RestController
@RequestMapping("/klasses")
public class KlassController {
	@Autowired
	KlassRepository klassRepository;

	@Autowired
	LeaderRepository leaderRepository;

	@Transactional
	@GetMapping(path = "")
	public List<KlassDTO> findAllKlasses(){
		return klassRepository.findAll().stream()
				.map(klass -> new KlassDTO(klass))
				.collect(Collectors.toList());
	}

	@Transactional
	@GetMapping(path = "/{id}")
	public KlassDTO findKlassById(@PathVariable Long id){
		return new KlassDTO(klassRepository.findById(id).get());
	}


	// TODO: how to return http status code and data
	@Transactional
	@PostMapping(path = "")
	public Klass addKlass(@RequestBody Klass klass){
		klass.getLeader().setKlass(klass);
		return klassRepository.save(klass);
	}

	@Transactional
	@PutMapping(path = "/{id}")
	public KlassDTO updateKlassInfo(@PathVariable Long id , @RequestBody Klass klass){
		Klass klass1 = klassRepository.findById(id).get();
		klass1.setName(klass.getName()!=null? klass.getName(): klass1.getName());
		return new KlassDTO(klassRepository.save(klass1));
	}

	@Transactional
	@PutMapping(path = "/{id}/leader")
	public KlassDTO updateKlassDetail(@PathVariable Long id, @RequestBody Leader leader){
		Klass klass = klassRepository.findById(id).get();
		Leader leader1 = klass.getLeader();
		leader1.setName(leader.getName()!=null
				? leader.getName()
				: leader1.getName());
		leader1.setAge(leader.getAge()!=0
				? leader.getAge()
				: leader1.getAge());
		klass.setLeader(leader1);
		leaderRepository.save(leader1);
		klassRepository.save(klass);
		return new KlassDTO(klass);
	}

	@Transactional
	@DeleteMapping(path = "/{id}")
	public KlassDTO deleteKlass(@PathVariable Long id){
		Klass klass = klassRepository.findById(id).get();
		klassRepository.delete(klass);
		return new KlassDTO(klass);
	}

}
