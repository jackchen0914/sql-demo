package org.example.utils;

import lombok.RequiredArgsConstructor;
import org.example.mapper.TransactionTypesMapper;
import org.example.pojo.McMrktPO;

public class PropertyConverUtils {

    public static String standardizeCurrencyCode(String ccy) {

        if (ccy == null) return null;

        if (ccy.contains("RMB")) {
            return "CNY";
        } else if (ccy.contains("YEN")) {
            return "JPY";
        }
        return ccy;
    }

    public static String marketCodeConver(String market) {
        String code = "";
        switch (market) {
            case "AUS":
                code = "AUS";
                break;
            case "BOD":
            case "BON":
            case "DEO":
            case "DEV":
            case "FUN":
            case "MMF":
                code = "OTC";
                break;
            case "CAN":
                code = "TSX";
                break;
            case "CHA":
                code = "SZSEA";
                break;
            case "CHE":
                code = "SIX";
                break;
            case "DEU":
                code = "FSE";
                break;
            case "FRA":
                code = "PARIS";
                break;
            case "HKG":
                code = "HKEX";
                break;
            case "JPN":
                code = "OSE";
                break;
            case "KOR":
                code = "KSE";
                break;
            case "MAL":
                code = "KLSE";
                break;
            case "SHA":
                code = "HKSSE";
                break;
            case "SHB":
                code = "SSE";
                break;
            case "SIN":
                code = "SGX";
                break;
            case "SZA":
                code = "HKSZSE";
                break;
            case "SZB":
                code = "SZSE";
                break;
            case "TWN":
                code = "TSEC";
                break;
            case "UKG":
                code = "LSE";
                break;
            case "USA":
                code = "NYSEMKT";
                break;
        }
        return code;
    }

    public static String divAnnStatusCovert(String status) {
        switch (status) {
            case "NEW":
            case "PEND":
                return "PENDING";
            case "FRZ":
                return "AUTHORIZED";
            case "APP":
                return "COMPLETED";
        }
        return status;
    }

    public static String fundStatCdeTrans(String code) {
        switch (code) {
            case "NEW":
                return "DRAFT";
            case "DEL":
                return "DELETED";
            case "REJ":
                return "REJECTED";
            case "APP":
                return "APPROVED";
            case "CAN":
                return "REVERSED";
            case "CANREQ":
                return "APPROVED";
            default:
                return " ";
        }
    }

    public static String stkStatCdeTrans(String location) {
        switch (location) {
            case "NOM":
                return "NOMINEE";
            case "OWM":
                return "OWNERNAME";
            case "BONUS":
                return "BONUS";
            case "ST":   //return "DIP(Deposit)/WIP(Withdraw)";
                return "WIP";
            default:
                return " ";
        }
    }

}
