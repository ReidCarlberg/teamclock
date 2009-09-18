/*
 * Created on May 6, 2003
 *
 */
package com.fivesticks.time.common.util;

import java.util.Vector;

/**
 * This class is a hack. Quicker than writing everything to a persistence layer.
 * 
 * @author Reid S Carlberg
 * @version 1.0.0
 */
public class GeographicCollection {

    private Vector states;

    private Vector nonStates;

    private Vector countries;

    public static GeographicCollection singleton = new GeographicCollection();

    public GeographicCollection() {
        setupCountries();
        setupStates();

    }

    private void setupStates() {
        Vector states = new Vector();
        Vector nonStates = new Vector();

        //	states.add(new GeographicVO("AL", "Alabama"));
        //		states.add(new GeographicVO("AK", "Alaska"));
        //		states.add(new GeographicVO("AS", "American Samoa"));
        //		states.add(new GeographicVO("AZ", "Arizona"));
        //		states.add(new GeographicVO("AR", "Arkansas"));
        //		states.add(new GeographicVO("CA", "California"));
        //		states.add(new GeographicVO("CO", "Colorado"));
        //		states.add(new GeographicVO("CT", "Connecticut"));
        //		states.add(new GeographicVO("DE", "Delaware"));
        //		states.add(new GeographicVO("DC", "District of Columbia"));
        //		states.add(new GeographicVO("FM", "Federated States of Micronesia"));
        //		states.add(new GeographicVO("FL", "Florida"));
        //		states.add(new GeographicVO("GA", "Georgia"));
        //		states.add(new GeographicVO("GU", "Guam"));
        //		states.add(new GeographicVO("HI", "Hawaii"));
        //		states.add(new GeographicVO("ID", "Idaho"));
        //		states.add(new GeographicVO("IL", "Illinois"));
        //		states.add(new GeographicVO("IN", "Indiana"));
        //		states.add(new GeographicVO("IA", "Iowa"));
        //		states.add(new GeographicVO("KS", "Kansas"));
        //		states.add(new GeographicVO("KY", "Kentucky"));
        //		states.add(new GeographicVO("LA", "Louisiana"));
        //		states.add(new GeographicVO("ME", "Maine"));
        //		states.add(new GeographicVO("MH", "Mashall Islands"));
        //		states.add(new GeographicVO("MD", "Maryland"));
        //		states.add(new GeographicVO("MA", "Massachusetts"));
        //		states.add(new GeographicVO("MI", "Michigan"));
        //		states.add(new GeographicVO("MN", "Minnesota"));
        //		states.add(new GeographicVO("MS", "Mississippi"));
        //		states.add(new GeographicVO("MO", "Missouri"));
        //		states.add(new GeographicVO("MT", "Montana"));
        //		states.add(new GeographicVO("NE", "Nebraska"));
        //		states.add(new GeographicVO("NV", "Nevada"));
        //		states.add(new GeographicVO("NH", "New Hampshire"));
        //		states.add(new GeographicVO("NJ", "New Jersey"));
        //		states.add(new GeographicVO("NM", "New Mexico"));
        //		states.add(new GeographicVO("NY", "New York"));
        //		states.add(new GeographicVO("NC", "North Carolina"));
        //		states.add(new GeographicVO("ND", "North Dakota"));
        //		states.add(new GeographicVO("MP", "North Mariana Islands"));
        //		states.add(new GeographicVO("OH", "Ohio"));
        //		states.add(new GeographicVO("OK", "Oklahoma"));
        //		states.add(new GeographicVO("OR", "Oregon"));
        //		states.add(new GeographicVO("PA", "Pennsylvania"));
        //		states.add(new GeographicVO("PR", "Puerto Rico"));
        //		states.add(new GeographicVO("RI", "Rhode Island"));
        //		states.add(new GeographicVO("SC", "South Carolina"));
        //		states.add(new GeographicVO("SD", "South Dakota"));
        //		states.add(new GeographicVO("TN", "Tennessee"));
        //		states.add(new GeographicVO("TX", "Texas"));
        //		states.add(new GeographicVO("UT", "Utah"));
        //		states.add(new GeographicVO("VT", "Vermont"));
        //		states.add(new GeographicVO("VA", "Virginia"));
        //		states.add(new GeographicVO("VI", "Virgin Islands, U.S."));
        //		states.add(new GeographicVO("WA", "Washington"));
        //		states.add(new GeographicVO("WV", "West Vriginia"));
        //		states.add(new GeographicVO("WI", "Wisconsin"));
        //		states.add(new GeographicVO("WY", "Wyoming"));
        //		states.add(new GeographicVO("AA", "Armed Forces the Americas"));
        //		states.add(new GeographicVO("AE", "Armed Forces Europe"));
        //		states.add(new GeographicVO("AP", "Armed Forces Pacific"));

        states.add(new GeographicVO("AL", "Alabama"));
        states.add(new GeographicVO("AK", "Alaska"));
        //    nonStates.add(new GeographicVO("AB", "Alberta"));
        states.add(new GeographicVO("AZ", "Arizona"));
        states.add(new GeographicVO("AR", "Arkansas"));
        // nonStates.add(new GeographicVO("BC", "British Columbia"));
        states.add(new GeographicVO("CA", "California"));
        states.add(new GeographicVO("CO", "Colorado"));
        states.add(new GeographicVO("CT", "Connecticut"));
        states.add(new GeographicVO("DE", "Delaware"));
        states.add(new GeographicVO("DC", "District of Columbia"));
        states.add(new GeographicVO("FL", "Florida"));
        states.add(new GeographicVO("GA", "Georgia"));
        states.add(new GeographicVO("HI", "Hawaii"));
        states.add(new GeographicVO("ID", "Idaho"));
        states.add(new GeographicVO("IL", "Illinois"));
        states.add(new GeographicVO("IN", "Indiana"));
        states.add(new GeographicVO("IA", "Iowa"));
        states.add(new GeographicVO("KS", "Kansas"));
        states.add(new GeographicVO("KY", "Kentucky"));
        states.add(new GeographicVO("LA", "Louisiana"));
        states.add(new GeographicVO("ME", "Maine"));
        //  nonStates.add(new GeographicVO("MB", "Manitoba"));
        states.add(new GeographicVO("MD", "Maryland"));
        states.add(new GeographicVO("MA", "Massachusetts"));
        states.add(new GeographicVO("MI", "Michigan"));
        states.add(new GeographicVO("MN", "Minnesota"));
        states.add(new GeographicVO("MS", "Mississippi"));
        states.add(new GeographicVO("MO", "Missouri"));
        states.add(new GeographicVO("MT", "Montana"));
        states.add(new GeographicVO("NE", "Nebraska"));
        states.add(new GeographicVO("NV", "Nevada"));
        //  nonStates.add(new GeographicVO("NB", "New Brunswick"));
        states.add(new GeographicVO("NH", "New Hampshire"));
        states.add(new GeographicVO("NJ", "New Jersey"));
        states.add(new GeographicVO("NM", "New Mexico"));
        states.add(new GeographicVO("NY", "New York"));
        //  nonStates.add(new GeographicVO("NF", "Newfoundland"));
        states.add(new GeographicVO("NC", "North Carolina"));
        states.add(new GeographicVO("ND", "North Dakota"));
        //  nonStates.add(new GeographicVO("NT", "Northwest Territory"));
        //  nonStates.add(new GeographicVO("NS", "Nova Scotia"));
        states.add(new GeographicVO("OH", "Ohio"));
        states.add(new GeographicVO("OK", "Oklahoma"));
        // nonStates.add(new GeographicVO("ON", "Ontario"));
        states.add(new GeographicVO("OR", "Oregon"));
        states.add(new GeographicVO("PA", "Pennsylvania"));
        //   nonStates.add(new GeographicVO("PE", "Prince Edward Island"));
        nonStates.add(new GeographicVO("PR", "Puerto Rico"));
        //  nonStates.add(new GeographicVO("PQ", "Quebec"));
        states.add(new GeographicVO("RI", "Rhode Island"));
        //  nonStates.add(new GeographicVO("SK", "Saskatchewan"));
        states.add(new GeographicVO("SC", "South Carolina"));
        states.add(new GeographicVO("SD", "South Dakota"));
        states.add(new GeographicVO("TN", "Tennessee"));
        states.add(new GeographicVO("TX", "Texas"));
        states.add(new GeographicVO("UT", "Utah"));
        states.add(new GeographicVO("VT", "Vermont"));
        nonStates.add(new GeographicVO("VI", "Virgin Islands"));
        states.add(new GeographicVO("VA", "Virginia"));
        states.add(new GeographicVO("WA", "Washington"));
        states.add(new GeographicVO("WV", "West Virginia"));
        states.add(new GeographicVO("WI", "Wisconsin"));
        states.add(new GeographicVO("WY", "Wyoming"));
        //  nonStates.add(new GeographicVO("YU", "Yukon Territory"));
        nonStates.add(new GeographicVO("GU", "Guam"));
        nonStates.add(new GeographicVO("AA", "Armed Forces the Americas"));
        nonStates.add(new GeographicVO("AE", "Armed Forces Europe"));
        nonStates.add(new GeographicVO("AP", "Armed Forces Pacific"));

        nonStates.add(new GeographicVO("MH", "Marshall Islands"));
        nonStates.add(new GeographicVO("MP", "Northern Mariana Islands"));
        nonStates.add(new GeographicVO("FM", "Federated States of Micronesia"));
        nonStates.add(new GeographicVO("PW", "Palau"));
        nonStates.add(new GeographicVO("AS", "American Samoa"));

        setNonStates(nonStates);
        setStates(states);
    } 

    private void setupCountries() {
        Vector country = new Vector();
        //		country.add(new GeographicVO("USA", "United States"));
        //		//country.put("Canada", "Canada");

        country.add(new GeographicVO("US", "United States"));
        country.add(new GeographicVO("CA", "Canada"));
        country.add(new GeographicVO("MX", "Mexico"));
        country.add(new GeographicVO("UK", "United Kingdom"));
        country.add(new GeographicVO("--", "---"));
        country.add(new GeographicVO("AF", "Afghanistan"));
        country.add(new GeographicVO("AL", "Albania"));
        country.add(new GeographicVO("DZ", "Algeria"));
        country.add(new GeographicVO("AS", "American Samoa"));
        country.add(new GeographicVO("AD", "Andorra"));
        country.add(new GeographicVO("AO", "Angola"));
        country.add(new GeographicVO("AI", "Anguilla"));
        country.add(new GeographicVO("AQ", "Antarctica"));
        country.add(new GeographicVO("AG", "Antigua/Barbuda"));
        country.add(new GeographicVO("AR", "Argentina"));
        country.add(new GeographicVO("AM", "Armenia"));
        country.add(new GeographicVO("AW", "Aruba"));
        country.add(new GeographicVO("AU", "Australia"));
        country.add(new GeographicVO("AT", "Austria"));
        country.add(new GeographicVO("AZ", "Azerbaijan"));
        country.add(new GeographicVO("BS", "Bahamas"));
        country.add(new GeographicVO("BH", "Bahrain"));
        country.add(new GeographicVO("BD", "Bangladesh"));
        country.add(new GeographicVO("BB", "Barbados"));
        country.add(new GeographicVO("BY", "Belarus"));
        country.add(new GeographicVO("BE", "Belgium"));
        country.add(new GeographicVO("BZ", "Belize"));
        country.add(new GeographicVO("BJ", "Benin"));
        country.add(new GeographicVO("BM", "Bermuda"));
        country.add(new GeographicVO("BT", "Bhutan"));
        country.add(new GeographicVO("BO", "Bolivia"));
        country.add(new GeographicVO("BA", "Bosnia"));
        country.add(new GeographicVO("BW", "Botswana"));
        country.add(new GeographicVO("BV", "Bouvet Island"));
        country.add(new GeographicVO("BR", "Brazil"));
        country.add(new GeographicVO("IO", "British Indian Ocean Territory"));
        country.add(new GeographicVO("WI", "British West Indies"));
        country.add(new GeographicVO("BN", "Brunei Darussalam"));
        country.add(new GeographicVO("BG", "Bulgaria"));
        country.add(new GeographicVO("BF", "Burkina Faso"));
        country.add(new GeographicVO("BU", "Burma"));
        country.add(new GeographicVO("BI", "Burundi"));
        country.add(new GeographicVO("KH", "Cambodia"));
        country.add(new GeographicVO("CM", "Cameroon"));
        country.add(new GeographicVO("CA", "Canada"));
        country.add(new GeographicVO("CV", "Cape Verde"));
        country.add(new GeographicVO("KY", "Cayman Islands"));
        country.add(new GeographicVO("CF", "Central Africa"));
        country.add(new GeographicVO("TD", "Chad"));
        country.add(new GeographicVO("CL", "Chile"));
        country.add(new GeographicVO("CN", "China"));
        country.add(new GeographicVO("CX", "Christmas Island"));
        country.add(new GeographicVO("CC", "Cocos Islands"));
        country.add(new GeographicVO("CO", "Colombia"));
        country.add(new GeographicVO("KM", "Comoros"));
        country.add(new GeographicVO("CG", "Congo"));
        country.add(new GeographicVO("CK", "Cook Islands"));
        country.add(new GeographicVO("CR", "Costa Rica"));
        country.add(new GeographicVO("CI", "Cote D'Ivoire"));
        country.add(new GeographicVO("HR", "Croatia"));
        country.add(new GeographicVO("CU", "Cuba"));
        country.add(new GeographicVO("CY", "Cyprus"));
        country.add(new GeographicVO("CZ", "Czech Republic"));
        country.add(new GeographicVO("DK", "Denmark"));
        country.add(new GeographicVO("DJ", "Djibouti"));
        country.add(new GeographicVO("DM", "Dominica"));
        country.add(new GeographicVO("DO", "Dominican Republic"));
        country.add(new GeographicVO("TP", "East Timor"));
        country.add(new GeographicVO("EC", "Ecuador"));
        country.add(new GeographicVO("EG", "Egypt"));
        country.add(new GeographicVO("SV", "El Salvador"));
        country.add(new GeographicVO("GB", "England"));
        country.add(new GeographicVO("GQ", "Equatorial Guinea"));
        country.add(new GeographicVO("ER", "Eritrea"));
        country.add(new GeographicVO("EE", "Estonia"));
        country.add(new GeographicVO("ET", "Ethiopia"));
        country.add(new GeographicVO("FO", "Faeroe Islands"));
        country.add(new GeographicVO("FK", "Falkland Islands"));
        country.add(new GeographicVO("FJ", "Fiji"));
        country.add(new GeographicVO("FI", "Finland"));
        country.add(new GeographicVO("FR", "France"));
        country.add(new GeographicVO("PF", "French Polynesia"));
        country.add(new GeographicVO("TF", "French Southern Territories"));
        country.add(new GeographicVO("GA", "Gabon"));
        country.add(new GeographicVO("GM", "Gambia"));
        country.add(new GeographicVO("XA", "Gaza"));
        country.add(new GeographicVO("GE", "Georgia"));
        country.add(new GeographicVO("DE", "Germany"));
        country.add(new GeographicVO("GH", "Ghana"));
        country.add(new GeographicVO("GI", "Gibraltar"));
        country.add(new GeographicVO("GR", "Greece"));
        country.add(new GeographicVO("GL", "Greenland"));
        country.add(new GeographicVO("GD", "Grenada"));
        country.add(new GeographicVO("GP", "Guadeloupe"));
        //   country.add(new GeographicVO("GU", "Guam"));
        country.add(new GeographicVO("GT", "Guatemala"));
        country.add(new GeographicVO("GF", "Guiana"));
        country.add(new GeographicVO("GN", "Guinea"));
        country.add(new GeographicVO("GW", "Guinea-Bissau"));
        country.add(new GeographicVO("GY", "Guyana"));
        country.add(new GeographicVO("HT", "Haiti"));
        country.add(new GeographicVO("HM", "Heard And Mcdonald Islands"));
        country.add(new GeographicVO("XH", "Held Territories"));
        country.add(new GeographicVO("HN", "Honduras"));
        country.add(new GeographicVO("HK", "Hong Kong"));
        country.add(new GeographicVO("HU", "Hungary"));
        country.add(new GeographicVO("IS", "Iceland"));
        country.add(new GeographicVO("IN", "India"));
        country.add(new GeographicVO("XI", "Indian Ocean Islands"));
        country.add(new GeographicVO("ID", "Indonesia"));
        country.add(new GeographicVO("IR", "Iran"));
        country.add(new GeographicVO("IQ", "Iraq"));
        country.add(new GeographicVO("IE", "Ireland"));
        country.add(new GeographicVO("IL", "Israel"));
        country.add(new GeographicVO("IT", "Italy"));
        country.add(new GeographicVO("JM", "Jamaica"));
        country.add(new GeographicVO("JP", "Japan"));
        country.add(new GeographicVO("JO", "Jordan"));
        country.add(new GeographicVO("KZ", "Kazakhstan"));
        country.add(new GeographicVO("KE", "Kenya"));
        country.add(new GeographicVO("KI", "Kiribati"));
        country.add(new GeographicVO("KR", "Korea"));
        country.add(new GeographicVO("KW", "Kuwait"));
        country.add(new GeographicVO("KG", "Kyrgyzstan"));
        country.add(new GeographicVO("LA", "Laos"));
        country.add(new GeographicVO("LV", "Latvia"));
        country.add(new GeographicVO("LB", "Lebanon"));
        country.add(new GeographicVO("LS", "Lesotho"));
        country.add(new GeographicVO("LR", "Liberia"));
        country.add(new GeographicVO("LY", "Libya"));
        country.add(new GeographicVO("LI", "Liechtenstein"));
        country.add(new GeographicVO("LT", "Lithuania"));
        country.add(new GeographicVO("LU", "Luxembourg"));
        country.add(new GeographicVO("MO", "Macau"));
        country.add(new GeographicVO("MK", "Macedonia"));
        country.add(new GeographicVO("MG", "Madagascar"));
        country.add(new GeographicVO("MW", "Malawi"));
        country.add(new GeographicVO("MY", "Malaysia"));
        country.add(new GeographicVO("MV", "Maldives"));
        country.add(new GeographicVO("ML", "Mali"));
        country.add(new GeographicVO("MT", "Malta"));
        country.add(new GeographicVO("MH", "Marshall Islands"));
        country.add(new GeographicVO("MQ", "Martinique"));
        country.add(new GeographicVO("MR", "Mauritania"));
        country.add(new GeographicVO("MU", "Mauritius"));
        country.add(new GeographicVO("YT", "Mayotte"));
        country.add(new GeographicVO("MX", "Mexico"));
        country.add(new GeographicVO("FM", "Micronesia"));
        country.add(new GeographicVO("MD", "Moldova"));
        country.add(new GeographicVO("MC", "Monaco"));
        country.add(new GeographicVO("MN", "Mongolia"));
        country.add(new GeographicVO("MS", "Montserrat"));
        country.add(new GeographicVO("MA", "Morocco"));
        country.add(new GeographicVO("MZ", "Mozambique"));
        country.add(new GeographicVO("MM", "Myanmar"));
        country.add(new GeographicVO("NA", "Namibia"));
        country.add(new GeographicVO("NR", "Nauru"));
        country.add(new GeographicVO("NP", "Nepal"));
        country.add(new GeographicVO("NL", "Netherlands"));
        country.add(new GeographicVO("AN", "Netherlands Antilles"));
        country.add(new GeographicVO("NC", "New Caledonia"));
        country.add(new GeographicVO("NZ", "New Zealand"));
        country.add(new GeographicVO("NI", "Nicaragua"));
        country.add(new GeographicVO("NE", "Niger"));
        country.add(new GeographicVO("NG", "Nigeria"));
        country.add(new GeographicVO("NU", "Niue"));
        country.add(new GeographicVO("NF", "Norfolk Island"));
        country.add(new GeographicVO("KP", "North Korea"));
        country.add(new GeographicVO("XB", "Northern Ireland"));
        country.add(new GeographicVO("MP", "Northern Mariana Islands"));
        country.add(new GeographicVO("NO", "Norway"));
        country.add(new GeographicVO("OM", "Oman"));
        country.add(new GeographicVO("PK", "Pakistan"));
        country.add(new GeographicVO("PW", "Palau"));
        country.add(new GeographicVO("PA", "Panama"));
        country.add(new GeographicVO("PG", "Papua New Guinea"));
        country.add(new GeographicVO("PY", "Paraguay"));
        country.add(new GeographicVO("PE", "Peru"));
        country.add(new GeographicVO("PH", "Philippines"));
        country.add(new GeographicVO("PN", "Pitcairn"));
        country.add(new GeographicVO("PL", "Poland"));
        country.add(new GeographicVO("PT", "Portugal"));
        country.add(new GeographicVO("PR", "Puerto Rico"));
        country.add(new GeographicVO("QA", "Qatar"));
        country.add(new GeographicVO("RE", "Reunion"));
        country.add(new GeographicVO("RO", "Romania"));
        country.add(new GeographicVO("RU", "Russia"));
        country.add(new GeographicVO("RW", "Rwanda"));
        country.add(new GeographicVO("LC", "Saint Lucia"));
        country.add(new GeographicVO("SM", "San Marino"));
        country.add(new GeographicVO("ST", "Sao Tome And Principe"));
        country.add(new GeographicVO("SA", "Saudi Arabia"));
        country.add(new GeographicVO("WY", "Scotland"));
        country.add(new GeographicVO("SN", "Senegal"));
        country.add(new GeographicVO("SC", "Seychelles"));
        country.add(new GeographicVO("SL", "Sierra Leone"));
        country.add(new GeographicVO("SG", "Singapore"));
        country.add(new GeographicVO("SK", "Slovak Republic"));
        country.add(new GeographicVO("SI", "Slovenia"));
        country.add(new GeographicVO("SB", "Solomon Islands"));
        country.add(new GeographicVO("SO", "Somalia"));
        country.add(new GeographicVO("ZA", "South Africa"));
        country.add(new GeographicVO("GS", "South Georgia"));
        country.add(new GeographicVO("ES", "Spain"));
        country.add(new GeographicVO("LK", "Sri Lanka"));
        country.add(new GeographicVO("SH", "St. Helena"));
        country.add(new GeographicVO("KN", "St. Kitts & Nevis"));
        country.add(new GeographicVO("PM", "St. Pierre"));
        country.add(new GeographicVO("VC", "St. Vincent & The Grendadines"));
        country.add(new GeographicVO("SD", "Sudan"));
        country.add(new GeographicVO("SR", "Suriname"));
        country.add(new GeographicVO("SJ", "Svalbard And Jan Mayen Islands"));
        country.add(new GeographicVO("SZ", "Swaziland"));
        country.add(new GeographicVO("SE", "Sweden"));
        country.add(new GeographicVO("CH", "Switzerland"));
        country.add(new GeographicVO("SY", "Syria"));
        country.add(new GeographicVO("TW", "Taiwan"));
        country.add(new GeographicVO("TJ", "Tajikistan"));
        country.add(new GeographicVO("TZ", "Tanzania"));
        country.add(new GeographicVO("TH", "Thailand"));
        country.add(new GeographicVO("TG", "Togo"));
        country.add(new GeographicVO("TK", "Tokelau"));
        country.add(new GeographicVO("TO", "Tonga"));
        country.add(new GeographicVO("TT", "Trinidad And Tobago"));
        country.add(new GeographicVO("TN", "Tunisia"));
        country.add(new GeographicVO("TR", "Turkey"));
        country.add(new GeographicVO("TM", "Turkmenistan"));
        country.add(new GeographicVO("TC", "Turks And Caicos Islands"));
        country.add(new GeographicVO("TV", "Tuvalu"));
        country.add(new GeographicVO("UM", "U.S. Minor Outlying Islands"));
        country.add(new GeographicVO("UG", "Uganda"));
        country.add(new GeographicVO("UA", "Ukraine"));
        country.add(new GeographicVO("AE", "United Arab Emirates"));
        country.add(new GeographicVO("US", "United States"));
        country.add(new GeographicVO("UY", "Uruguay"));
        country.add(new GeographicVO("UZ", "Uzbekistan"));
        country.add(new GeographicVO("VU", "Vanuatu"));
        country.add(new GeographicVO("VA", "Vatican City State"));
        country.add(new GeographicVO("VE", "Venezuela"));
        country.add(new GeographicVO("VN", "Viet Nam"));
        country.add(new GeographicVO("VG", "Virgin Islands - British"));
        country.add(new GeographicVO("VI", "Virgin Islands - U.S"));
        country.add(new GeographicVO("WX", "Wales"));
        country.add(new GeographicVO("WF", "Wallis And Futuna Islands"));
        country.add(new GeographicVO("EH", "Western Sahara"));
        country.add(new GeographicVO("WS", "Western Samoa"));
        country.add(new GeographicVO("YE", "Yemen"));
        country.add(new GeographicVO("YU", "Yugoslavia"));
        country.add(new GeographicVO("ZM", "Zambia"));
        country.add(new GeographicVO("ZW", "Zimbabwe"));

        setCountries(country);
    }

    /**
     * @return
     */
    public Vector getCountries() {
        return countries;
    }

    /**
     * @return
     */
    public Vector getAllRegions() {
        Vector ret = new Vector();
        ret.addAll(this.getStates());
        ret.addAll(this.getNonStates());

        return ret;
    }

    public Vector getStates() {
        return states;
    }

    /**
     * @param map
     */
    private void setCountries(Vector map) {
        countries = map;
    }

    /**
     * @param map
     */
    private void setStates(Vector map) {
        states = map;
    }

    public Vector getNonStates() {
        return nonStates;
    }

    public void setNonStates(Vector nonStates) {
        this.nonStates = nonStates;
    }
}