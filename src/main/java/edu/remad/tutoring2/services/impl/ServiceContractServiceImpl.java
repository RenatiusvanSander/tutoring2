package edu.remad.tutoring2.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.remad.tutoring2.models.ServiceContractEntity;
import edu.remad.tutoring2.repositories.ServiceContractEntityRepository;
import edu.remad.tutoring2.services.ServiceContractService;

@Service
public class ServiceContractServiceImpl implements ServiceContractService {

	private final ServiceContractEntityRepository serviceContractEntityRepository;

	@Autowired
	public ServiceContractServiceImpl(ServiceContractEntityRepository serviceContractEntityRepository) {
		this.serviceContractEntityRepository = serviceContractEntityRepository;
	}

	@Override
	public ServiceContractEntity createServiceContract(ServiceContractEntity serviceContract) {
		ServiceContractEntity createdServiceContract = serviceContractEntityRepository.saveAndFlush(serviceContract);

		return createdServiceContract;
	}

	@Override
	public List<ServiceContractEntity> createMultipleServiceContract(List<ServiceContractEntity> serviceContracts) {
		List<ServiceContractEntity> createdServiceContracts = serviceContractEntityRepository
				.saveAllAndFlush(serviceContracts);

		return createdServiceContracts;
	}

	@Override
	public ServiceContractEntity renameServiceContract(ServiceContractEntity serviceContractToRename) {
		ServiceContractEntity updatedserviceContract = null;
		Optional<ServiceContractEntity> serviceContractTobeUpdated = serviceContractEntityRepository
				.findById(serviceContractToRename.getServiceContractNo());

		// ChangeDate to add
		if (!serviceContractTobeUpdated.isEmpty()) {
			updatedserviceContract = serviceContractTobeUpdated.get();
			updatedserviceContract
					.setServiceContractDescription(serviceContractToRename.getServiceContractDescription());
			updatedserviceContract.setServiceContractName(serviceContractToRename.getServiceContractName());

			updatedserviceContract = serviceContractEntityRepository.saveAndFlush(updatedserviceContract);
		} else {
			throw new RuntimeException("ServiceContract to remane not found or database error.");
		}

		return updatedserviceContract;
	}

	@Override
	public List<ServiceContractEntity> renameMultipleServiceContract(
			List<ServiceContractEntity> serviceContractsToRename) {
		List<Long> serviceContractNos = serviceContractsToRename.isEmpty() ? new ArrayList<Long>()
				: serviceContractsToRename.stream().map(ServiceContractEntity::getServiceContractNo)
						.collect(Collectors.toList());
		List<ServiceContractEntity> serviceContractsToUpdate = serviceContractEntityRepository
				.findAllById(serviceContractNos);
		List<ServiceContractEntity> updatedServiceContracts = new ArrayList<>();

		if (!serviceContractsToUpdate.isEmpty()) {
			for (int i = 0; (serviceContractsToRename.size() == serviceContractsToUpdate.size())
					&& (i < serviceContractsToRename.size()); i++) {
				ServiceContractEntity serviceContractToRename = serviceContractsToRename.get(i);
				ServiceContractEntity serviceContractToUpdate = serviceContractsToUpdate.get(i);
				serviceContractToUpdate
						.setServiceContractDescription(serviceContractToRename.getServiceContractDescription());
				serviceContractToUpdate.setServiceContractName(serviceContractToRename.getServiceContractName());

				updatedServiceContracts.add(serviceContractToUpdate);
			}

			if (!updatedServiceContracts.isEmpty()) {
				updatedServiceContracts = serviceContractEntityRepository.saveAllAndFlush(updatedServiceContracts);
			} else {
				throw new RuntimeException("ServiceContracts to remane not found or database error.");
			}
		} else {
			throw new RuntimeException("ServiceContracts to remane not found or database error.");
		}

		return updatedServiceContracts;
	}

	@Override
	public ServiceContractEntity deleteServiceContract(ServiceContractEntity serviceContractToDelete) {
		serviceContractEntityRepository.delete(serviceContractToDelete);

		return serviceContractToDelete;
	}

	@Override
	public List<ServiceContractEntity> deleteMultipleServiceContracts(
			List<ServiceContractEntity> serviceContractsToDelete) {
		serviceContractEntityRepository.deleteAll(serviceContractsToDelete);
		
		return serviceContractsToDelete;
	}

}
