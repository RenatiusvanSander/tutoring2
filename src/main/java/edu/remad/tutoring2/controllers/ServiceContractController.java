package edu.remad.tutoring2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/create-service-contract", produces="application/json")
	public ResponseEntity<ServiceContractEntity> createServiceContract(@RequestBody ServiceContractEntity serviceContract) {
		checkAuthenticated();
		
		ServiceContractEntity createdServiceContract = serviceContractService.createServiceContract(serviceContract);
		
		return ResponseEntity.ok(createdServiceContract);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create-service-contracts")
	public List<ServiceContractEntity> createMultipleServiceContract(List<ServiceContractEntity> serviceContracts) {
		checkAuthenticated();
		List<ServiceContractEntity> createdServiceContracts = serviceContractService.createMultipleServiceContract(serviceContracts);
		
		return createdServiceContracts;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/rename-service-contract")
	public ServiceContractEntity renameServiceContract(ServiceContractEntity renameServiceContract) {
		checkAuthenticated();
		
		ServiceContractEntity renamedServiceContract = serviceContractService.renameServiceContract(renameServiceContract);
		
		return renamedServiceContract;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/rename-service-contracts")
	public List<ServiceContractEntity> renameMultipleServiceContract(List<ServiceContractEntity> serviceContractsToRename) {
		List<ServiceContractEntity> renamedServiceContracts = serviceContractService.renameMultipleServiceContract(serviceContractsToRename);
		
		return renamedServiceContracts;
	}
	
	@DeleteMapping("/delete-service-contract")
	@PreAuthorize("hasRole('ADMIN')")
	public ServiceContractEntity deleteServiceContract(ServiceContractEntity serviceContractToDelete) {
		ServiceContractEntity deletedServiceContract = serviceContractService.deleteServiceContract(serviceContractToDelete);
		
		return deletedServiceContract;
	}
	
	@DeleteMapping("/delete-service-contracts")
	@PreAuthorize("hasRole('ADMIN')")
	public List<ServiceContractEntity> deleteMultipleServiceContracts(List<ServiceContractEntity> serviceContractsToDelete) {
		List<ServiceContractEntity> deletedServiceContracts = serviceContractService.deleteMultipleServiceContracts(serviceContractsToDelete);
		
		return deletedServiceContracts;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get-service-contract-by-id/{id}")
	public ServiceContractEntity getServiceContract(@PathVariable("id") Long id) {
		ServiceContractEntity loadedServiceContract = serviceContractService.findServiceContractById(id);
		
		return loadedServiceContract;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get-service-contracts-by-ids/{ids}")
	public List<ServiceContractEntity> getMultipleServiceContracts(@PathVariable("ids") List<Long> Ids) {
		List<ServiceContractEntity> loadedServiceContracts = serviceContractService.getMultipleServiceContracts(Ids);
		
		return loadedServiceContracts;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get-all-service-contracts")
	public List<ServiceContractEntity> getAllServiceContracts() {
		return serviceContractService.getAllServiceContracts();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get-service-contract")
	public ServiceContractEntity getServiceContract(ServiceContractEntity serviceContract) {
		ServiceContractEntity loadedServiceContract = serviceContractService.getServiceContract(serviceContract.getServiceContractNo());
		
		return loadedServiceContract;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/get-service-contracts")
	public List<ServiceContractEntity> getServiceContracts(List<ServiceContractEntity> serviceContracts) {
		
		List<ServiceContractEntity> loadedServiceContracts = serviceContractService.getServiceContracts(serviceContracts);
		
		return loadedServiceContracts;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/find-service-contract-by-id/{id}")
	public ServiceContractEntity findServiceContractById(@PathVariable("id") Long id) {
		 ServiceContractEntity foundServiceContractEntity = serviceContractService.findServiceContractById(id);
		 
		 return foundServiceContractEntity;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/find-service-contracts-by-ids/{ids}")
	public List<ServiceContractEntity> findServiceContractsByIds(@PathVariable("ids") List<Long> ids) {
		List<ServiceContractEntity> foundServiceContracts = serviceContractService.findServiceContractsByIds(ids);
		
		return foundServiceContracts;
	}
}
