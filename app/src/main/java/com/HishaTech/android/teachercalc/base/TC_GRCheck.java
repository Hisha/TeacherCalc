package com.HishaTech.android.teachercalc.base;

import android.content.Context;
import android.content.SharedPreferences;

import com.HishaTech.android.teachercalc.Utils;

public class TC_GRCheck {

    public TC_GRCheck() {

    }

    public boolean verifyA100(int AHigh) {

        if (AHigh == 100) {

            return true;

        } else {

            return false;

        }

    }

    public boolean verifyAvalues(int AHigh, int ALow) {

        if (AHigh > ALow) {

            return true;

        } else {

            return false;

        }

    }

    public boolean verifyAtoB(int ALow, int BHigh) {

        if (ALow == BHigh + 1) {

            return true;

        } else {

            return false;

        }

    }

    public boolean verifyBvalues(int BHigh, int BLow) {

        if (BHigh > BLow) {

            return true;

        } else {

            return false;

        }
    }

    public boolean verifyBtoC(int BLow, int CHigh) {

        if (BLow == CHigh + 1) {

            return true;

        } else {

            return false;

        }
    }

    public boolean verifyCvalues(int CHigh, int CLow) {

        if (CHigh > CLow) {

            return true;

        } else {

            return false;

        }
    }

    public boolean verifyCtoD(int CLow, int DHigh) {

        if (CLow == DHigh + 1) {

            return true;

        } else {

            return false;

        }
    }

    public boolean verifyDvalues(int DHigh, int DLow) {

        if (DHigh > DLow) {

            return true;

        } else {

            return false;

        }
    }

    public boolean verifyDtoE(int DLow, int EHigh) {

        if (DLow == EHigh + 1) {

            return true;

        } else {

            return false;

        }
    }

    public boolean verifyEvalues(int EHigh, int ELow) {

        if (EHigh > ELow) {

            return true;

        } else {

            return false;

        }
    }

    public boolean verifyEtoF(int EHigh, int ELow, int FHigh, int FLow,
                              Context context) {

        SharedPreferences prefs = context.getSharedPreferences(Utils
                .pref_name, 0);

        boolean EisF = prefs.getBoolean(Utils.EisF_Key, Utils.EisF_Default);

        if (!EisF) {

            if (ELow == FHigh + 1) {

                return true;

            } else {

                return false;

            }

        } else {

            if (EHigh == FHigh) {

                if (ELow == FLow) {

                    return true;

                } else {

                    return false;

                }

            } else {

                return false;

            }

        }

    }

    public boolean verifyFvalues(int FHigh, int FLow) {

        if (FHigh > FLow) {

            return true;

        } else {

            return false;

        }
    }

    public boolean verifyF0(int FLow) {

        if (FLow == 0) {

            return true;

        } else {

            return false;

        }

    }

}
