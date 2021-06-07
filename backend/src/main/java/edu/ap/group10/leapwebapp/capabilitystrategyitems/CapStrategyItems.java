
package edu.ap.group10.leapwebapp.capabilitystrategyitems;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import edu.ap.group10.leapwebapp.capability.Capability;
import edu.ap.group10.leapwebapp.strategy_item.StrategyItem;
import lombok.Data;

@Entity
@Data
public class CapStrategyItems {

    // primary key
    @Id
    @Column(name = "capstrategyitems_id")
    Long id;

    // foreign keys
    // Capability many-to-many Resource => CapResource intermediate class
    @OneToOne(targetEntity = Capability.class)
    @JoinColumn(name = "capability_id")
    private Capability capability;

    @OneToOne(targetEntity = StrategyItem.class)
    @JoinColumn(name = "strategyitem_id")
    private StrategyItem strategyItem;

    // columns
    @Column
    @Enumerated(EnumType.STRING)
    private StrategicEmphasis strategicEmphasis;

    // CONSTRUCTORS
    public CapStrategyItems() {
    }

    public CapStrategyItems(Capability cap, StrategyItem strItem, StrategicEmphasis strategicEmphasis) {
        setId(Long.parseLong((cap.getId().toString() + strItem.getId().toString())));
        setCapability(cap);
        setStrategyItem(strItem);
        setStrategicEmphasis(strategicEmphasis);
    }

}
