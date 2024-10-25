/**
 * The SeatLayout class stores the layout of seats in the airplane, divided by class (First Class,
 * Economy Plus, Economy).
 * CS151 Hw2 Solution
 * Instructor: Dr.Kim
 * @author: Angie Do
 * Date: 10/02/2024
 */

import java.util.Arrays;
import java.util.List;

public class SeatLayout {

    // First Class Seats
    public static final List<String> firstClassSeats = Arrays.asList(
        "1A", "1B", "1D", "1E", "1F", "1G", "1K", "1L",
        "2A", "2B", "2D", "2E", "2F", "2G", "2K", "2L",
        "3A", "3B", "3D", "3E", "3F", "3G", "3K", "3L",
        "4A", "4B", "4K", "4L"
    );
    

    // Economy Plus Seats
    public static final List<String> economyPlusSeats = Arrays.asList(
    "16D", "16E", "16F", "16G", "16J", "16K", 
    "17A", "17B", "17C", "17D", "17E", "17F", "17G", "17J", "17K", "17L", 
    "18A", "18B", "18C", "18D", "18E", "18F", "18G", "18J", "18K", "18L", 
    "19A", "19B", "19C", "19D", "19E", "19F", "19G", "19J", "19K", "19L",
    "20A", "20B", "20C", "20D", "20E", "20F", "20G", "20J", "20K", "20L",
    "21A", "21B", "21C", "21D", "21E", "21F", "21G", "21J", "21K", "21L",
    "22A", "22B", "22C", "22D", "22E", "22F", "22G", "22J", "22K", "22L",
    "23A", "23B", "23C", "23D", "23E", "23F", "23G", "23J", "23K", "23L",
    "24A", "24B", "24C", "24J", "24K", "24L",
    "25A", "25B", "25C", "25J", "25K", "25L",
    "26A", "26B", "26C", "26J", "26K", "26L", 
    "39A", "39B", "39C", "39J", "39K", "39L",
    "40D", "40E", "40F", "40G" 
);

    // Economy Seats
    public static final List<String> economySeats = Arrays.asList(
    "24D", "24E", "24F", "24G",
    "25D", "25E", "25F", "25G",
    "26D", "26E", "26F", "26G",
    "27A", "27B", "27C", "27D", "27E", "27F", "27G", "27J", "27K", "27L", 
    "28A", "28B", "28C", "28D", "28E", "28F", "28G", "28J", "28K", "28L",
    "29A", "29B", "29C", "29D", "29E", "29F", "29G", "29J", "29K", "29L",
    "30A", "30B", "30C", "30D", "30E", "30F", "30G", "30J", "30K", "30L",
    "31A", "31B", "31C", "31D", "31E", "31F", "31G", "31J", "31K", "31L",
    "32A", "32B", "32C", "32D", "32E", "32F", "32G", "32J", "32K", "32L",
    "33A", "33B", "33C", "33D", "33E", "33F", "33G", "33J", "33K", "33L",
    "34A", "34B", "34C", "34D", "34E", "34F", "34G", "34J", "34K", "34L",
    "35A", "35B", "35C", "35D", "35E", "35F", "35G", "35J", "35K", "35L",
    "36A", "36B", "36C", "36D", "36E", "36F", "36G", "36J", "36K", "36L", 
    "37A", "37B", "37K", "37L", 
    "40A", "40B", "40C", "40J", "40K", "40L", 
    "41A", "41B", "41C", "41D", "41E", "41F", "41G", "41J", "41K", 
    "42A", "42B", "42C", "42D", "42E", "42F", "42G", "42J", "42K",
    "43A", "43B", "43C", "43D", "43E", "43F", "43G", "43J", "43K",
    "44A", "44B", "44C", "44D", "44E", "44F", "44G", "44J", "44K",
    "45A", "45B", "45C", "45D", "45E", "45F", "45G", "45J", "45K",
    "46A", "46B", "46C", "46D", "46E", "46F", "46G", "46J", "46K",
    "47A", "47B", "47C", "47D", "47E", "47F", "47G", "47J", 
    "48A", "48B", "48D", "48E", "48F", "48G",
    "49A", "49B", "49D", "49E", "49F", "49G", 
    "50A", "50B", "50D", "50E", "50F", "50G", 
    "51A", "51B", "51D", "51E", "51F", "51G", 
    "52A", "52B", "52D", "52E", "52F", "52G",
    "53D", "53E", "53F", "53G" 
);

}
