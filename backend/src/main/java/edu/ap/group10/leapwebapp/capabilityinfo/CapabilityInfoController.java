package edu.ap.group10.leapwebapp.capabilityinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CapabilityInfoController {

    @Autowired
    private CapabilityInfoService capabilityInfoService;

    @GetMapping("/capabilityinformation/{capabilityid}")
    public List<CapabilityInfo> getAllCapabilityInfo(@PathVariable Long capabilityid) {
        return capabilityInfoService.getAllCapabilityInfo(capabilityid);
    }

    @GetMapping("/capabilityinformation/information/{informationid}")
    public List<CapabilityInfo> getAllCapabilityInfoByInfo(@PathVariable Long informationid) {
        return capabilityInfoService.getAllCapabilityInfoInformation(informationid);
    }

    @PostMapping("/capabilityinformation/{capabilityid}")
    public void addCapabilityInfo(@PathVariable Long capabilityid, @RequestParam Long information,
            @RequestParam Criticality criticality) {
        capabilityInfoService.addCapabilityInfo(capabilityid, information, criticality);
    }

    @PutMapping("/capabilityinformation/{capinfoid}")
    public void updateCapabilityInfo(@PathVariable Long capinfoid, @RequestParam Criticality criticality) {
        capabilityInfoService.updateCapabilityInfo(capinfoid, criticality);
    }

    @DeleteMapping("/capabilityinformation/{capinfoid}")
    public void addCapabilityInfo(@PathVariable Long capinfoid) {
        capabilityInfoService.deleteCapabilityInfo(capinfoid);
    }
}
