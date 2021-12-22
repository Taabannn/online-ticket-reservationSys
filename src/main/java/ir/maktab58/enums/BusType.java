package ir.maktab58.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Taban Soleymani
 */
@AllArgsConstructor
@NoArgsConstructor
public enum BusType {
    VIP_CLASSICUS("vip-classicus"),
    CIP_CLASSICUS("cip-classicus"),
    ORDINARY_CLASSICUS("ordinary-classicus"),
    VIP("vip"),
    ORDINARY("ordinary");

    private @Getter String type;
}
