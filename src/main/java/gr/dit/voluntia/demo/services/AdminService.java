package gr.dit.voluntia.demo.services;

import gr.dit.voluntia.demo.models.*;
import gr.dit.voluntia.demo.repositories.AdminRepository;
import gr.dit.voluntia.demo.repositories.EventRepository;
import gr.dit.voluntia.demo.repositories.OrganizationRepository;
import gr.dit.voluntia.demo.repositories.VolunteerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService  {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private VolunteerRepository volunteerRepository;
    @Autowired
    private EventRepository eventRepository;


    // Admin's Methods
    /// ////////////////////////////////////////////////////////////////////////////
    
    /**
     * Description:
     * Retrieves an Admin object based on his id.
     * @param id of searching admin
     * @return an Admin object or null of Admin with this id does not exists
     * * */
    public Admin getAdmin(Long id) {
        Optional<Admin> admin = adminRepository.findById(id);
        return admin.isPresent() ? admin.get() : null;
    }


    // Event Methods
    /// ////////////////////////////////////////////////////////////////////////////
    
    /**
     * Description:
     * Retrieve all events
     * @return List of all events in the database
     * * */
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    /**
     * Description:
     * Retrieve all pending events
     * @return List of pending events
     * * */
    public List<Event> getPendingEvents() {
        return eventRepository.findAllPendingEvents();
    }

    /**
     * Description:
     * Approve a pending event by its id
     * @param eventId of event that we want to approve
     * * */
    @Transactional
    public void approveEventById(Long eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        event.ifPresent(e -> {
            e.setStatus("confirmed");
            eventRepository.save(e);
        });
    }

    /**
     * Description:
     * Reject a pending event by its id
     * @param eventId of event that we want to reject
     * * */
    @Transactional
    public void rejectEventById(Long eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        event.ifPresent(e -> {
            e.setStatus("rejected");
            eventRepository.save(e);
        });
    }



    // Organization Methods
    /// ////////////////////////////////////////////////////////////////////////////
    
    /**
     * Description:
     * Retrieve all organization
     * @return List of organization instances from database
     * * */
    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    /**
     * Description:
     * Retrieve all pending organization
     * @return List of pending organizations
     * * */
    public List<Organization> getPendingOrganizations() {
        return organizationRepository.findAllPendingOrganizations();
    }

    /**
     * Description:
     * Approve a pending organization by its id
     * @param orgId of event that we want to approve
     * * */
    @Transactional
    public void approveOrganizationById(Long orgId) {
        Optional<Organization> organization = organizationRepository.findById(orgId);
        organization.ifPresent(org -> {
            org.setAccountStatus("approved");
            organizationRepository.save(org);
        });
    }

    /**
     * Description:
     * Reject a pending organization by its id
     * @param orgId of event that we want to reject
     * * */
    @Transactional
    public void rejectOrganizationById(Long orgId) {
        Optional<Organization> organization = organizationRepository.findById(orgId);
        organization.ifPresent(org -> {
            org.setAccountStatus("rejected");
            organizationRepository.save(org);
        });
    }

    

    // Volunteer Methods
    /// ////////////////////////////////////////////////////////////////////////////
    
    /**
     * Description:
     * Retrieve all volunteers
     * @return List of all volunteers
     * * */
    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    /**
     * Description:
     * Retrieve all pending volunteers
     * @return List of pending volunteers
     * * */
    public List<Volunteer> getPendingVolunteers() {
        return volunteerRepository.findAllPendingVolunteers();
    }

    /**
     * Description:
     * Approve a pending organization by its id
     * @param volId of event that we want to approve
     * * */
    @Transactional
    public void approveVolunteerById(Long volId) {
        Optional<Volunteer> volunteer = volunteerRepository.findById(volId);
        volunteer.ifPresent(vol -> {
            vol.setAccountStatus("approved");
            volunteerRepository.save(vol);
        });
    }

    /**
     * Description:
     * Reject a pending volunteer by its id
     * @param volId of event that we want to reject
     * * */
    @Transactional
    public void rejectVolunteerById(Long volId) {
        Optional<Volunteer> volunteer = volunteerRepository.findById(volId);
        volunteer.ifPresent(vol -> {
            vol.setAccountStatus("rejected");
            volunteerRepository.save(vol);
        });
    }

}





