package edu.remad.tutoring2.services;

import java.util.List;

import edu.remad.tutoring2.models.ServiceContractEntity;

public interface ServiceContractService {

	ServiceContractEntity createServiceContract(ServiceContractEntity serviceContract);

	List<ServiceContractEntity> createMultipleServiceContract(List<ServiceContractEntity> serviceContracts);

	ServiceContractEntity renameServiceContract(ServiceContractEntity serviceContractToRename);

	List<ServiceContractEntity> renameMultipleServiceContract(List<ServiceContractEntity> serviceContractsToRename);

	ServiceContractEntity deleteServiceContract(ServiceContractEntity serviceContractToDelete);

	List<ServiceContractEntity> deleteMultipleServiceContracts(List<ServiceContractEntity> serviceContractsToDelete);

}
