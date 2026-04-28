import java.util.*;

class Patient {
    String name;
    int age;
    String[] symptoms;
    String diagnosis;
    String doctor;
    String medicine;

    Patient(String name, int age, String[] symptoms) {
        this.name = name;
        this.age = age;
        this.symptoms = symptoms;
    }

    void displayInfo() {
        System.out.println("\nPatient Name: " + name);
        System.out.println("Age: " + age);
        System.out.print("Symptoms: ");
        for (String symptom : symptoms) {
            System.out.print(symptom + ", ");
        }
        System.out.println("\nDiagnosis: " + diagnosis);
        System.out.println("Doctor: " + doctor);
        System.out.println("Medicine: " + medicine);
    }
}

class HospitalExpertSystem {
    ArrayList<Patient> patients = new ArrayList<>();

    void addPatient(Patient patient) {
        patients.add(patient);
        diagnosePatient(patient);
    }

    void diagnosePatient(Patient patient) {
        String combined = String.join(" ", patient.symptoms).toLowerCase();

        if (combined.contains("fever") && combined.contains("cough")) {
            patient.diagnosis = "Flu";
            patient.doctor = "General Physician";
            patient.medicine = "Paracetamol";
        } else if (combined.contains("headache") || combined.contains("dizziness")) {
            patient.diagnosis = "Migraine";
            patient.doctor = "Neurologist";
            patient.medicine = "Ibuprofen";
        } else if (combined.contains("stomach") || combined.contains("nausea") || combined.contains("vomiting")) {
            patient.diagnosis = "Gastritis";
            patient.doctor = "Gastroenterologist";
            patient.medicine = "Antacid";
        } else if (combined.contains("sore throat") || combined.contains("runny nose") || combined.contains("cold")) {
            patient.diagnosis = "Common Cold";
            patient.doctor = "General Physician";
            patient.medicine = "Cough Syrup";
        } else if (combined.contains("rash") || combined.contains("itching")) {
            patient.diagnosis = "Allergic Reaction";
            patient.doctor = "Dermatologist";
            patient.medicine = "Antihistamine";
        } else {
            patient.diagnosis = "Observation Required";
            patient.doctor = "General Physician";
            patient.medicine = "Further tests needed";
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HospitalExpertSystem system = new HospitalExpertSystem();

        System.out.println("Hospital Expert System");
        System.out.print("Enter patient name (or type 'quit' to exit): ");
        String name = sc.nextLine().trim();

        while (!name.equalsIgnoreCase("quit")) {
            System.out.print("Enter age: ");
            int age = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Enter symptoms separated by commas: ");
            String symptomLine = sc.nextLine().trim();
            String[] symptoms = Arrays.stream(symptomLine.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .toArray(String[]::new);

            Patient patient = new Patient(name, age, symptoms);
            system.addPatient(patient);
            patient.displayInfo();

            System.out.println();
            System.out.print("Enter next patient name (or type 'quit' to exit): ");
            name = sc.nextLine().trim();
        }

        System.out.println("Exiting Hospital Expert System. Goodbye!");
        sc.close();
    }
}