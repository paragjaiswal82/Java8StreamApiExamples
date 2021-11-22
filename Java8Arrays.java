import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Java8Arrays {
    public static void main(String[] args) {
        List<Character> arr = Arrays.asList('a', 'b', 'c', 'a', 'b', 'd', 'e');
        System.out.println("Original characters "+arr);
        //Print distinct characters
        arr.stream().
                distinct().
                collect(Collectors.toList()).
                forEach(System.out::print);
                System.out.println();

        //Print only duplicate characters
        Set<Character> set = new HashSet<>();
        arr.stream().
                filter(x -> !set.add(x)).
                collect(Collectors.toSet()).
                forEach(System.out::print);
        System.out.println();

        //Print number of duplicate characters
        Map<Character, Long> m = arr.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Print number of duplicate characters - "+m);

        //Print data by account type
        List<String> email = new ArrayList<>();
        email.add("kgh@yahoo.com");
        email.add("klp@gmail.com");


        List<Person> person = Arrays.asList(new Person("Ajay",email,"CREDIT"),
                new Person("Vijay",email,"DEBIT"),
                new Person("Sujay",email,"CREDIT"),
                new Person("Puru",email,"DEBIT"));
        Map<String,List<Person>> listPerson = person.stream().
                                    collect(Collectors.groupingBy(Person::getAccountType,Collectors.toList()));
        System.out.println("Group by Account type - "+listPerson);

        //Print groupby numbers only if count > 1
        List<Integer> listG = Arrays.asList(5, 3, 4, 1, 3, 7, 2, 9, 9, 4);
        List<Integer> dupGroupBy = listG.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet()
                .stream()
                .filter(x -> x.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println("Print groupby numbers only if count > 1 - "+dupGroupBy);

        //Print person name if present
        String printPerson = person.stream()
                .filter(x -> "Ajay".equals(x.getName()))
                .map(Person::getName)
                .findAny()
                .orElse(null);
        System.out.println("Print person name if present- "+printPerson);

        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add(null);
        list.add("D");
        list.add("E");

        // filter null value
        list.stream()
                .filter(Objects::nonNull)
                .forEach(System.out::println);

        //reverse string
        String reverseNAme = "Welcome";
        reverseNAme = Stream.of(reverseNAme)
                .map(x -> new StringBuilder(x).reverse()) //(Collections.reverseOrder())
                .collect(Collectors.joining());
        System.out.println("reverse string- "+reverseNAme);

        //reverse number
        IntStream number = IntStream.of(1,2,3,4,5,6);
        int []numArray = number.toArray();
        IntStream reverseNumber = IntStream.range(1,numArray.length+1).boxed().mapToInt(i->numArray[numArray.length-i]);
        System.out.println("Reverse number");
        reverseNumber.forEach(x->System.out.println(x));

        int num = 123456;
        int reverseNum=0;
        while(num>0){
            int reminder=num%10;
            reverseNum=reverseNum*10+reminder;
            num=num/10;
        }
        System.out.println("reverseNum - "+reverseNum);

        // keep order, it is always a,b,c,1,2,3
        Stream<String> s = Stream.of("a", "b", "c", "1", "2", "3");
        s.parallel().forEachOrdered(x -> System.out.println(x));

        //List<Person> to Map - avoid duplicate key
        Map<String,String> mapPerson= person.stream().collect(Collectors.toMap(x->x.getAccountType(),y->y.getName(),
                                                                                (old,latest)->old));

        Map<String,List<String>> mapPerson1= person.stream()
                                                .sorted(Comparator.comparing(Person::getAccountType).reversed())
                                                .collect(Collectors.toMap(x->x.getAccountType(),y->y.getEmail(),
                (old,latest)->old));
        System.out.println("List<Person> to Map - avoid duplicate key - "+mapPerson1);

        List<String> emailList = person.stream()
                .flatMap(x -> x.getEmail().stream())
                .collect(Collectors.toList());
        System.out.println(emailList);

        long count = IntStream.of(1,2,3,4,5).count();
        System.out.println("count "+count);

        //find common elements in both arrays
        List<String> list1 = Arrays.asList("ABC","DEF","GHI","KPI");
        List<String> list2 = Arrays.asList("AAA","DEF","XYZ","KPI");

        List<String> result = list1.stream()
                .filter(x ->{
                    if (list2.contains(x)){
                        return true;
                    }else
                        return false;
                })
                .collect(Collectors.toList());
        System.out.println("common elements in both arrays - "+result);

        //min, max, avaerage, count, sum
        //find first 2 distinct numbers
         IntStream.of(2,1,5,4,0,7,0,1)
                .distinct()
                .sorted()
                 .limit(2) //skip(2), boxed() - convert each num to Integer, anyMatch() , allMatch()
                 .forEach(System.out::println);

         //Get first 3 highest paid employees name
        List<Employee> employee = Arrays.asList(new Employee("Ajay",5000,30,"Accounts"),
                new Employee("Vijay",35000,30,"Logistics"),
                new Employee("Sujay",25000,20,"Logistics"),
                new Employee("Amit",15000,30,"Logistics"),
                new Employee("Sanket",50000,40,"Accounts"),
                new Employee("Sameer",10000,50,"Accounts"));
        employee.stream()
                .sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                .limit(3)
                .map(Employee::getName)
                .forEach(System.out::println);
        Map<String,List<Employee>> groupDept= employee.stream()
                .parallel() //if input is too large
                .collect(Collectors.groupingBy(e ->e.getDepartment()));
        System.out.println("Group by department - "+groupDept);

        //Optional - to avoid NullPointerException
        Employee emp = new Employee("Ajay",4000,0,"Accounts");
        Optional optionalAge = Optional.ofNullable(emp.getAge());
//        if(optionalAge.isPresent()){
//
//        }
        System.out.println(optionalAge.orElse("30"));

        // reduce
        int sum = Stream.of(1,2,3)
                .reduce(0,(a,b)->a+b);
        System.out.println("Sum of - "+sum);

        int sumOf = Stream.of(1,2,3)
                .mapToInt(i->i)
                .sum();
        System.out.println("Sum of - "+sumOf);

        //Null checks

        /*List<String> listOfStuffFiltered = Optional.ofNullable(listOfStuff)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());*/

        List<String> listOfStuff = Arrays.asList("abc","fgc",null,"hhh",null);
        List<String> listOfStuffFiltered = listOfStuff.stream()
                .filter(Objects::nonNull)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(listOfStuffFiltered);

        String str = "Welcome To Cognizant";
        char ch[] = str.toCharArray();
        Map<Character,Integer> map = new HashMap<>();
        int cnt1=1;
        for(Character c:ch){
            if(map.containsKey(c)){
                map.put(c,map.get(c)+1);
            }else{
                map.put(c,cnt1);
            }
        }

       // System.out.println(map);
       // Duplicate Characters
       Map<Object,Long> mpp= str.chars().mapToObj(c -> (char)c).collect(Collectors.groupingBy(c->c,Collectors.counting()))
                       .entrySet().stream()
                        .filter(x->x.getValue()>=2)
                        .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(old,latest)->old));

        System.out.println("Duplicate Characters are -"+mpp);

        //find max age of employee
        int maxAge = employee.stream().mapToInt(Employee::getAge).max().getAsInt();
        System.out.println("maxAge- "+maxAge);

        //Get young employee
        List<String> youngEmployee = employee.stream().filter(x->x.getAge()<30).map(Employee::getName).collect(Collectors.toList());
        System.out.println("Get young employee- "+youngEmployee);
        //find max numbers
        List<Integer> numList = Arrays.asList(3,1,9,6,5,3);
        System.out.println("Max number - "+numList.stream().mapToInt(x->x).max().getAsInt());

        int []intArray = {3,1,9,6,5,3};
        System.out.println("Max number in array -"+Arrays.stream(intArray).boxed().mapToInt(x->x).max().getAsInt());

        //Sorting employee byNameAge
        List<Employee> sortNameAge = employee.stream().sorted(Comparator.comparing(Employee::getName).thenComparingInt(Employee::getAge))
                .collect(Collectors.toList());
        System.out.println("Sorting employee byNameAge -"+sortNameAge);

        //find pincodes
        List<Address> listAddress = Arrays.asList(new Address(410507,"chakan road"),
                new Address(410508,"mumbai road"),
                new Address(510507,"pune road"),
                new Address(610507,"pipry road"),
                new Address(410507,"chakan road"));
        List<Entity> entity = Arrays.asList(new Entity("Suyash",listAddress),
                new Entity("Mahesh",listAddress));
        Set<Integer> pincodes = entity.stream().flatMap(x->x.getAddress().stream()).map(Address::getPincode).collect(Collectors.toSet());
        System.out.println("find pincodes - "+pincodes);

        //findFirstNonRepeatedCharacter
        Map<Character, Long> countMap = new LinkedHashMap<>(str.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(c -> c, Collectors.counting())).
        entrySet().stream().filter(entry -> entry.getValue() == 1).map(Map.Entry::getKey).findFirst().orElseThrow(RuntimeException::new));

        //average salary of each department?
        Map<String, Double> mp=employee.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(e -> e.getSalary())));
        System.out.println("average salary of each department - "+mp);
        //Get Highest paid employee from each Dept
        Map<String, Optional<Employee>> mp1=employee.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.maxBy(Comparator.comparing(Employee::getSalary))));
        System.out.println("Get Highest paid employee from each Dept - "+mp1);

        //Get the details of youngest male employee in the product development department
        /*employee.stream()
                .filter(e -> e.getDepartment().equals(department))
                .filter(e -> e.getGender().equals(gender))
                .collect(Collectors.minBy(Comparator.comparing(Employee::getAge)));*/

        //Who has the most working experience in the organization
        //employees.stream().min(Comparator.comparing(Employee::getYearOfJoining));

        //How many male and female employees are there in the sales and marketing team
        //employee.stream().filter(em -> emp.getDepartment().equals(department)).collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));



        /*//merge arrays
        String []arr1 = {"ab","cd"};
        String []arr2 = {"ef"};
        String[] concat = Stream.concat(Arrays.stream(arr1),Arrays.stream(arr2)).toArray(String[]::new);
        System.out.println("Concateneated array- "+concat.toString());

        IntStream.rangeClosed(1,50).
                peek(i-> System.out.println("processing count -"+i)).
                mapToObj(i->new Address(123456,"abc road")).
                collect(Collectors.toList());*/

    }
}

 class Person{
    private String name;
    private List<String> email;
    private String accountType;

     public Person(String name, List<String> email, String accountType) {
         this.name = name;
         this.email = email;
         this.accountType = accountType;
     }

     public void setName(String name) {
         this.name = name;
     }

     public void setEmail(List<String> email) {
         this.email = email;
     }

     public void setAccountType(String accountType) {
         this.accountType = accountType;
     }

     public String getName() {
         return name;
     }

     public List<String> getEmail() {
         return email;
     }

     public String getAccountType() {
         return accountType;
     }

     @Override
     public String toString() {
         return "Person{" +
                 "name='" + name + '\'' +
                 ", email=" + email +
                 ", accountType='" + accountType + '\'' +
                 '}';
     }
     }
class Employee{
    private String name;
    private int salary;
    private int age;
    private String department;

    public Employee(String name, int salary, int age, String department) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.department = department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", age='" + age + '\'' +
                ", department='" + department + '\'' +
                '}';
    }


    }
class Entity{
    String name;
    List<Address> address;

    public Entity(String name, List<Address> address) {
        this.name = name;
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public List<Address> getAddress() {
        return address;
    }

    }
class Address{
    int pincode;
    String firstAddress;

    public Address(int pincode, String firstAddress) {
        this.pincode = pincode;
        this.firstAddress = firstAddress;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public void setFirstAddress(String firstAddress) {
        this.firstAddress = firstAddress;
    }

    public int getPincode() {
        return pincode;
    }

    public String getFirstAddress() {
        return firstAddress;
    }
}
