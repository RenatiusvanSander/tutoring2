package edu.remad.tutoring2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.remad.tutoring2.models.ServiceContractEntity;
import edu.remad.tutoring2.security.interfaces.IAuthenticationFacade;
import edu.remad.tutoring2.services.ServiceContractService;

@RestController
@RequestMapping("/api/servicecontract")
public class ServiceContractController extends AuthenticatedController{
	
	private final ServiceContractService serviceContractService;
	
	@Autowired
	public ServiceContractController(ServiceContractService serviceContractService, IAuthenticationFacade authenticationFacade) {
		super(authenticationFacade);
		
		this.serviceContractService = serviceContractService;
	}

	@PostMapping("/create-service-contract")
	public ServiceContractEntity createServiceContract(ServiceContractEntity serviceContract) {
		checkAuthenticated();
		
		ServiceContractEntity createdServiceContract = serviceContractService.createServiceContract(serviceContract);
		
		return createdServiceContract;
	}
	
	@PostMapping("/create-service-contracts")
	public List<ServiceContractEntity> createMultipleServiceContract(List<ServiceContractEntity> serviceContracts) {
		checkAuthenticated();
		List<ServiceContractEntity> createdServiceContracts = serviceContractService.createMultipleServiceContract(serviceContracts);
		
		return createdServiceContracts;
	}
}
