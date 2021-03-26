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
	@Autowired
	private AppUserRepository appUser;
	@Autowired
	private AppRolaRepository rola;
	@Autowired
	private CurrencyRepository currencies;
	@Autowired
	private ApplicationTransferRepository applicationTransfer;
	@Autowired
	private PlacementTypeCategoryRepository placementTypeCategory;
	@Autowired
	private CompetencyHolderRepository competencyHolder;
	@Autowired
	private HolidayRepository holidayRepository;
	@Autowired
	private Report1Repository report1;
	@Autowired
	private Report2Repository report2;
	@Autowired
	private Report21Repository report21;
	@Autowired
	private Report3Repository report3;
	
	public CurrencyRepository Currencies() {
		return currencies;
	}
	
	@Autowired
	private PlacementTimeConsumentRepository placementTime;
	@Autowired
	private TaskTimeConsumentRepository taskTime;
	
	@Autowired
	private TransferRulesRepository transferRules;
	
	@Autowired
	private DocumentReceiveRepository documentReceive;
	
	
	
	public DocumentReceiveRepository documentReceive()
	{
		return documentReceive;
	}
	
	public PlacementTimeConsumentRepository placementTime() {
		return placementTime;
	}
	public TaskTimeConsumentRepository taskTime() {
		return taskTime;
	}
	
	
	public AppUserRepository AppUser() {
		return appUser;
	}
	public AppRolaRepository Rola() {
		return rola;
	}

	public ApplicationRepository Application() {
		return applications;
	}
	public ClientBusinesPartnerRepository ClientBusinesPartner() {
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
	public PlacementStatusRepository PlacementStatus() {
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
	public TaskRepository Task() {
		return tasks;
	}
	public TaskStatusRepository TaskStatus() {
		return taskStatuses;
	}
	public UserRepository User() {
		return useres;
	}
	
	public ApplicationTransferRepository ApplicationTransfer() {
		return applicationTransfer;
	}
	
	public PlacementTypeCategoryRepository PlacementTypeCategory()
	{
		return placementTypeCategory;
	}
	
	public TransferRulesRepository TransferRules()
	{
		return transferRules;
	}
	
	public HolidayRepository Holiday()
	{
		return holidayRepository;
	}
	
	
	public CompetencyHolderRepository CompetencyHolder()
	{
		return competencyHolder;	
	}
	
	public Report1Repository Report1()
	{
		return report1;	
	}
	
	public Report2Repository Report2()
	{
		return report2;	
	}
	
	public Report21Repository Report21()
	{
		return report21;	
	}
	
	public Report3Repository Report3()
	{
		return report3;	
	}
}
