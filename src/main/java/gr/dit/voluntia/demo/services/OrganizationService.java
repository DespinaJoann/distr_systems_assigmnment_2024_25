package gr.dit.voluntia.demo.services;

import gr.dit.voluntia.demo.dtos.SignInRequest;
import gr.dit.voluntia.demo.dtos.SignUpRequest;
import gr.dit.voluntia.demo.models.Organization;
import gr.dit.voluntia.demo.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    public Organization createNewOrganization(SignUpRequest request) {
        // Create a new organization instance with the
        Organization org = new Organization();
        org.setUsername(request.getUsername());
        org.setPassword(request.getPassword());
        org.setEmail(request.getEmail());
        org.setPhoneNumber(request.getPhoneNumber());
        org.setOrgName(request.getOrganizationName());
        org.setAddress(request.getAddress());
        org.setLocation(request.getLocation());

        // Save the created organization instance to the Data Base
        return organizationRepository.save(org);
    }

    public Organization findOrganization(SignInRequest request) {
        Organization org = organizationRepository.findByUsernameAndPassword(
                request.getUsername(),
                request.getPassword()
        );

        if (org == null) {
            throw new IllegalArgumentException("Invalid username or password.");
        }

        return org;
    }
}
