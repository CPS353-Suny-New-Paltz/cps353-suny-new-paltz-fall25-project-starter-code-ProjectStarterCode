package lab5;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import lab5.Date;
public class Test
{
	private static final String[] FIRST_NAMES = {"John", "Jane", "Michael", "Emily", "Chris", "Katie", "David", "Laura", "James", "Sarah"};
    private static final String[] LAST_NAMES = {"Smith", "Doe", "Johnson", "Brown", "Williams", "Jones", "Garcia", "Miller", "Davis", "Rodriguez"};
    private static final String[] SPECIALTIES = {"Cardiology", "Dermatology", "Neurology", "Pediatrics", "Surgery", "Psychiatry", "Radiology", "Ophthalmology", "Orthopedics", "Anesthesiology"};
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
    	List<Person> people = new ArrayList<>();
        
        for (int i = 0; i < 100; i++) {
            Name name = new Name(getRandomFirstName(), getRandomLastName());
            long ssn = generateRandomSSN();
            Date birthday = generateRandomDate();

            Person person = new Person(name, birthday, ssn);
            people.add(person);
        }

        // Print generated Person objects
        for (Person person : people) {
            System.out.println(person);
        }
        
        List<Doctor> doctors = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Name name = new Name(getRandomFirstName(), getRandomLastName());
            String[] specialties = getRandomSpecialties();
            double salary = generateRandomSalary();

            Doctor doctor = new Doctor(name, specialties, salary);
            doctors.add(doctor);
        }

        // Print generated Doctor objects
        for (Doctor doctor : doctors) {
            System.out.println(doctor);
        }
        
        List<Patient> patients = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Name name = new Name(getRandomFirstName(), getRandomLastName());
            Patient patient = new Patient(name);
            patients.add(patient);
        }

        // Print generated Patient objects
        for (Patient patient : patients) {
            System.out.println(patient);
        }
        
        List<Appointment> appointments = new ArrayList<>();

        for (int i = 0; i < 500; i++) {
            Date date = generateRandomDate();
            LocalTime time = generateRandomTime();
            long patientId = RANDOM.nextLong(5000, 5101); // Patient ID between 5000 and 5100
            long doctorId = RANDOM.nextLong(1000, 1101); // Doctor ID between 1000 and 1100

            Appointment appointment = new Appointment(date, time, patientId, doctorId);
            appointments.add(appointment);
        }

        // Print generated Appointment objects
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

       private static String[] getRandomSpecialties() {
        int numSpecialties = RANDOM.nextInt(3) + 1; // Random number of specialties (1-3)
        String[] selectedSpecialties = new String[numSpecialties];

        for (int i = 0; i < numSpecialties; i++) {
            String specialty;
            do {
                specialty = SPECIALTIES[RANDOM.nextInt(SPECIALTIES.length)];
            } while (contains(selectedSpecialties, specialty)); // Ensure no duplicates

            selectedSpecialties[i] = specialty;
        }
        return selectedSpecialties;
    }

    private static boolean contains(String[] array, String value) {
        for (String s : array) {
            if (s != null && s.equals(value)) {
                return true;
            }
        }
        return false;
    }

    private static double generateRandomSalary() {
        return 80000 + RANDOM.nextDouble() * 20000; // Salary between 80000 and 100000
    }
    
private static String getRandomFirstName() {
    return FIRST_NAMES[RANDOM.nextInt(FIRST_NAMES.length)];
}

private static String getRandomLastName() {
    return LAST_NAMES[RANDOM.nextInt(LAST_NAMES.length)];
}

private static long generateRandomSSN() {
    return 100000000 + RANDOM.nextLong(900000000); // Generates SSN in the range 100000000 to 999999999
}

private static Date generateRandomDate() {
    String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    int day = RANDOM.nextInt(31) + 1; // Days 1-31
    int monthIndex = RANDOM.nextInt(months.length);
    int year = RANDOM.nextInt(100) + 1920; // Random year between 1920 and 2019

    return new Date(months[monthIndex], day, year);
}

private static LocalTime generateRandomTime() {
    int hour = RANDOM.nextInt(0, 24); // Hours between 0 and 23
    int minute = RANDOM.nextInt(0, 60); // Minutes between 0 and 59
    return LocalTime.of(hour, minute);
}
}