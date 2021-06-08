
package edu.ap.group10.leapwebapp.capabilityresource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import edu.ap.group10.leapwebapp.capability.Capability;
import edu.ap.group10.leapwebapp.capability.CapabilityRepository;
import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.company.CompanyRepository;
import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentRepository;
import edu.ap.group10.leapwebapp.resource.Resource;
import edu.ap.group10.leapwebapp.resource.ResourceRepository;

@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class CapResourceRepositoryTests {

    @Autowired
    private CompanyRepository companyRepo;

    @Autowired
    private EnvironmentRepository environmentRepo;

    @Autowired
    private CapabilityRepository capabilityRepo;

    @Autowired
    private ResourceRepository resourceRepo;

    @Autowired
    private CapResourceRepository sut; // system under test

    @Test
    void GivenCapResources_WhenFindByCapabilityId_ThenReturnCapResources() {
        // Given
        Company company = new Company("", "", "", "", 1, 1, "", "", "", "");
        companyRepo.save(company);

        Environment environment = new Environment("", company);
        environmentRepo.save(environment);

        Capability capability1 = new Capability("", environment); // 3
        Capability capability2 = new Capability("", environment); // 4
        capabilityRepo.saveAll(Arrays.asList(capability1, capability2));

        Resource resource = new Resource("", "", 1, environment); // 5
        resourceRepo.save(resource);

        CapResource capresource1 = new CapResource(capability1, resource, 1); // 6 (3-5)
        CapResource capresource2 = new CapResource(capability2, resource, 1); // 7 (4-5)
        sut.saveAll(Arrays.asList(capresource1, capresource2));
        // When
        List<CapResource> actual = sut.findByCapabilityId(capability1.getId()); // Find by 3 -> Return 6 (3-5)
        // Then
        actual.forEach(capres -> {
            assertEquals(capability1.getId(), capres.getCapability().getId()); // 3=3?
        });
    }

    @Test
    void GivenCapResources_WhenFindByResourceId_ThenReturnCapResources() {
        // Given
        Company company = new Company("", "", "", "", 1, 1, "", "", "", "");
        companyRepo.save(company);

        Environment environment = new Environment("", company);
        environmentRepo.save(environment);

        Capability capability = new Capability("", environment); // 3
        capabilityRepo.save(capability);

        Resource resource1 = new Resource("", "", 1, environment); // 4
        Resource resource2 = new Resource("", "", 1, environment); // 5
        resourceRepo.saveAll(Arrays.asList(resource1, resource2));

        CapResource capresource1 = new CapResource(capability, resource1, 1); // 6 (3-4)
        CapResource capresource2 = new CapResource(capability, resource2, 1); // 7 (3-5)
        sut.saveAll(Arrays.asList(capresource1, capresource2));
        // When
        List<CapResource> actual = sut.findByResourceId(resource1.getId()); // Find by 4 -> Return 6 (3-4)
        // Then
        actual.forEach(capres -> {
            assertEquals(resource1.getId(), capres.getResource().getId()); // 4=4?
        });
    }

    @Test
    void GivenCapResources_WhenFindByCapabilityIdAndResourceId_ThenReturnCapResources() {
        // Given
        Company company = new Company("", "", "", "", 1, 1, "", "", "", "");
        companyRepo.save(company);

        Environment environment = new Environment("", company);
        environmentRepo.save(environment);

        Capability capability1 = new Capability("", environment); // 3
        Capability capability2 = new Capability("", environment); // 4
        capabilityRepo.saveAll(Arrays.asList(capability1, capability2));

        Resource resource1 = new Resource("", "", 1, environment); // 5
        Resource resource2 = new Resource("", "", 1, environment); // 6
        resourceRepo.saveAll(Arrays.asList(resource1, resource2));

        CapResource capresource1 = new CapResource(capability1, resource1, 1); // 7 (3-5)
        CapResource capresource2 = new CapResource(capability2, resource2, 1); // 8 (4-6)
        sut.saveAll(Arrays.asList(capresource1, capresource2));
        // When
        Boolean actual = sut.existsByCapabilityIdAndResourceId(capability1.getId(), resource1.getId());
        // Then
        assertTrue(actual); // Exists 3-5? -> Return true
    }

}
