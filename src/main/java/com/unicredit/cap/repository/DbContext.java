package com.unicredit.cap.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbContext {

	@Autowired
	private ApplicationRepository applications;
	@Autowired
	private ClientBusinesPartnerRepository clientBusinesPartners;
	@Autowired
	private DocumentRepository documents;
	@Autowired
	private DocumentTypeRepository documenttypes;
	@Autowired
	private OrganizationRepository orgnaizations;
	@Autowired
	private PlacementRepository placements;
	@Autowired
	private PlacementStatusRepository placementStatuses;
	@Autowired
	private PlacementTransferRepository placementTransfers;
	@Autowired
	private PlacementTypeRepository placementTypes;
	@Autowired
	private TaskDetailRepository taskDetails;
	@Autowired
	private TaskRepository tasks;
	@Autowired
	private TaskStatusRepository taskStatuses;
	@Autowired
	private UserRepository useres;
	
	
	public ApplicationRepository Application() {
		return applications;
	}
	public ClientBusinesPartnerRepository ClientBusinesPartners() {
		return clientBusinesPartners;
	}
	public DocumentRepository Document() {
		return documents;
	}
	public DocumentTypeRepository Documenttype() {
		return documenttypes;
	}
	public OrganizationRepository Orgnaization() {
		return orgnaizations;
	}
	public PlacementRepository Placement() {
		return placements;
	}
	public PlacementStatusRepository PlacementStatuse() {
		return placementStatuses;
	}
	public PlacementTransferRepository PlacementTransfer() {
		return placementTransfers;
	}
	public PlacementTypeRepository PlacementType() {
		return placementTypes;
	}
	public TaskDetailRepository TaskDetail() {
		return taskDetails;
	}
	public TaskRepository Tasks() {
		return tasks;
	}
	public TaskStatusRepository TaskStatuse() {
		return taskStatuses;
	}
	public UserRepository Usere() {
		return useres;
	}
	
	
	

	
	
	
}
