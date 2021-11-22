//
//package main.java.com.vijay.learning.stream;
//
//
//public class StreamOperations {
//
//    public static Integer findMaxNumber(List<Integer> numbers) {
//       // return Collections.max(numbers );
//        return numbers.stream().mapToInt(num -> num).max().getAsInt();
//    }
//    public static int findMaxNumber(int[] numbers) {
//        return Arrays.stream(numbers).boxed().mapToInt(num -> num).max().getAsInt();
//    }
//    public static List<Customer> getUnique(List<Customer> customers) {
//        return customers.stream().distinct().collect(Collectors.toList());
//    }
//
//
//
//     * get list of Customer names who's age is lesser than 30
//     **//*
//
//    public static List<String> getYoungCustomers(List<Customer> customers) {
//        List<String> result = customers.stream().filter(customer -> customer.getAge() < 30).map(Customer::getName).collect(Collectors.toList());
//        log.info("Customers: {}", customers);
//        return result;
//    }
//
//     /* get list of Customer names who's age is lesser than 30
//     **/
//
//    public static int getYoungCustomerCount(List<Customer> customers) {
//        return (int) customers.stream().filter(customer -> customer.getAge() < 30).count();
//    }
//
//    public static Customer getAnyCustWithNameMatch(List<Customer> customers, String name) {
//        //customers.stream().anyMatch(Customer -> Customer.getName().equals(name)) // returns boolean
//        Optional<Customer> emptyCustomer = Optional.empty();
//        Optional<Customer> customer = customers.stream().filter(emp -> emp.getName().equals(name)).findAny();
//
//    if (customer.isPresent()){
//            return customer.get();
//        } else {
//            return Customer.builder().build();
//        }*//*
//
//        return customer.orElse(Customer.builder().build());
//    }
//
//
///**
//     * Arithmetic Operations or Reduction Operations
//     **/
// // find max age customer later
//    public static void arithMeticOperations(List<Customer> customers) {
//        log.info("Max Age: {}", customers.stream().mapToInt(Customer::getAge).max());
//    }
//
//    public static List<Customer> sortByNameAndId(List<Customer> customers) {
//        return customers.stream().sorted(Comparator.comparing(Customer::getName).thenComparing(Customer::getId)).collect(Collectors.toList());
//    }
//
//    public static String getNamesPipeSeparate(List<Customer> customers) {
//        // Avoid duplicate Names
//        //return customers.stream().map(Customer::getName).distinct().collect(Collectors.joining("|"));
//        return customers.stream().map(Customer::getName).distinct().collect(Collectors.joining("|"));
//    }
//
//    public static Map<String, List<Customer>> groupByCustomer(List<Customer> customers) {
//        return customers.stream().collect(Collectors.groupingBy(Customer::getName));
//    }
//
//    public static Set<Integer> getPincodes(List<Customer> customers) {
//        return customers.stream().flatMap(customer -> customer.getAddresses().stream()).map(Address::getPin).collect(Collectors.toSet());
//        // address null check
//        //return customers.stream().map(Customer::getAddresses).filter(CollectionUtils::isNotEmpty).flatMap(Collection::stream).map(Address::getPin).collect(Collectors.toSet());
//    }
//
//    public static Map<String, List<Address>> getSameStreet(List<Customer> customers) {
//        return customers.stream().flatMap(customer -> customer.getAddresses().stream()).collect(Collectors.groupingBy(Address::getStreet));
//    }
//
//
///**
//     * Stream Operations of List of Strings
//     **/
//
//    public static String convertToString(List<String> names, String separator) {
//        // Using Stream
//        //return names.stream().collect(Collectors.joining(separator));
//
//        // Using String join
//        return String.join(separator, names);
//    }
//
//    public static List<String> convertToList(String namesStr, String separator) {
//        //return Arrays.asList(namesStr.split(separator));
//        return Stream.of(namesStr.split(separator)).collect(Collectors.toList());
//    }
//
//    public static List<String> sortNames(List<String> names) {
//        return names.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
//    }
//
//    public static List<String> sortNamesDescending(List<String> names) {
//        return names.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
//    }
//
//    public static List<String> limitNames(List<String> names, int limit) {
//        return names.stream().limit(limit).collect(Collectors.toList());
//    }
//
//    public static List<String> skipNames(List<String> names, int skip) {
//        return names.stream().skip(skip).collect(Collectors.toList());
//    }
//
//    public static Map<String, List<String>> getFilterMap(String filterValue, String mapSeparator, String keyValueSeparator, String valueSeparator) {
//        // "areaType.id:County;State;City;Mandal;Street,provider.id:Airtel;Jio;Hathway;ACT";
//        List<String> filters = Arrays.asList(filterValue.split(mapSeparator));
//        Map<String, List<String>> resultMap = new HashMap<>();
//        filters.forEach(filter -> {
//            List<String> keyValues = Arrays.asList(filter.trim().split(keyValueSeparator));
//            if (keyValues.size() != 2) {
//                throw new RuntimeException("Key and Values are invalid");
//            }
//            resultMap.put(keyValues.get(0), Arrays.asList(keyValues.get(1).split(valueSeparator)));
//        });
//        return resultMap;
//    }
//
//    public static Map<Integer, String> getAlphabeticValueMap(Map<Integer, String> inputMap) {
//        // allMatch(Character::isDigit)) => for Numbers
//        return inputMap.entrySet().stream()
//                .filter(map -> StringUtils.isNotBlank(map.getValue()) && map.getValue().chars().allMatch(Character::isAlphabetic))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//    }
//
//
///**
//     * Stream Operations of List of Numbers
//     **/
//
//    public static double getSquareSum(List<Integer> numbers) {
//        return numbers.stream().mapToDouble(number -> Math.pow(number, 2)).sum();
//    }
//
//    public static Map<Integer, Long> findFrequency(List<Integer> numbers) {
//        return numbers.stream().collect(Collectors.groupingBy(number -> number, Collectors.counting()));
//
//     Set<Integer> set = new HashSet<>(numbers);
//        for (Integer number : set) {
//            System.out.println("Frequency: "+ number+ "=>" + Collections.frequency(numbers, number));
//        }
//
//    }
//
//
///**
//     * Stream Operations on Employee problem solving
//     **/
//
//    // Sort by employee age and find max age
//    public static void sortEmployeeByAge(List<Employee> employees) {
//        List<Employee> result = employees.stream().sorted(Comparator.comparing(Employee::getAge)).collect(Collectors.toList());
//        log.info("Sort Employee: {}", result);
//        Integer maxAge = employees.stream().mapToInt(Employee::getAge).max().getAsInt();
//        log.info("Max age: {}", maxAge);
//    }
//
//    // 0. Sort by emp name with descending order
//    public static List<Employee> sortEmployeeByName(List<Employee> employees){
//        return  employees.stream().sorted(Comparator.comparing(Employee::getName, Comparator.reverseOrder())).collect(Collectors.toList());
//    }
//    // 1. How many male and female employees are there in the organization?
//    public static Integer findEmpCount(List<Employee> employees, String gender) {
//        return (int) employees.stream().filter(employee -> employee.getGender().equals(gender)).count();
//    }
//
//    public static Map<String, Long> findEmployeeCountByGender(List<Employee> employees) {
//        return employees.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
//    }
//
//    // 2. Print the name of all departments in the organization?
//    public static List<String> findAllDepartments(List<Employee> employees) {
//        //return employees.stream().map(Employee::getDepartment).collect(Collectors.toSet());
//        return employees.stream().map(Employee::getDepartment).distinct().collect(Collectors.toList());
//    }
//
//    // 3. What is the average age of male and female employees?
//    public static Map<String, Double> findAverageAgeByGender(List<Employee> employees) {
//        // By passing arg as gender
//        //employees.stream().filter(employee -> employee.getGender().equals(gender)).mapToInt(Employee::getAge).average();
//
//        // find min/max age by gender returns Map<String, Optional<Employee>>
//        //return employees.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.minBy(Comparator.comparing(Employee::getAge))));
//        //return employees.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.maxBy(Comparator.comparing(Employee::getAge))));
//
//        return employees.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
//    }
//
//    // 4. Get the details of highest paid employee in the organization?
//    public static Employee findHighestPaidEmployee(List<Employee> employees) {
//        // max salary
//        // return employees.stream().mapToDouble(employee -> employee.getSalary().doubleValue()).max().getAsDouble();
//        Optional<Employee> employee = employees.stream().max(Comparator.comparing(Employee::getSalary));
//        return employee.get();
//    }
//
//    // 5. Get the names of all employees who have joined after 2015?
//    public static List<String> getEmpNamesAfter2015(List<Employee> employees) {
//        return employees.stream().filter(employee -> employee.getYearOfJoining() > 2015).map(Employee::getName).collect(Collectors.toList());
//    }
//
//    // 6. Count the number of employees in each department
//    public static Map<String, Long> getEmpCountInDepartment(List<Employee> employees) {
//        // returns Map<String, List<Employee>>
//        //return employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));
//        return employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
//    }
//
//    // 7. What is the average salary of each department?
//    public static Map<String, Double> getAvgSalaryOfDepartment(List<Employee> employees) {
//        return employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(employee -> employee.getSalary().doubleValue())));
//    }
//
//    // 7.1 Get Highest paid employee from each Dept?
//    public static Map<String, Optional<Employee>> getMaxSalaryEmpOfDepartment(List<Employee> employees) {
//        return employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.maxBy(Comparator.comparing(Employee::getSalary))));
//    }
//
//    // 8 : Get the details of youngest male employee in the product development department?
//    public static Optional<Employee> getDeptYoungestMaleEmp(List<Employee> employees, String department, String gender) {
//
//    return employees.stream()
//                .filter(employee -> employee.getDepartment().equals(department))
//                .filter(employee -> employee.getGender().equals(gender))
//                .collect(Collectors.minBy(Comparator.comparing(Employee::getAge)));*//*
//
//        return employees.stream().filter(employee -> employee.getDepartment().equals(department) && employee.getGender().equals(gender)).min(Comparator.comparing(Employee::getAge));
//    }
//
//    // 9 : Who has the most working experience in the organization?
//    public static Optional<Employee> getMostExpEmp(List<Employee> employees) {
//        return employees.stream().min(Comparator.comparing(Employee::getYearOfJoining));
//    }
//
//    // 10 : How many male and female employees are there in the sales and marketing team?
//    public static Map<String, Long> getEmpCountByGenderInDept(List<Employee> employees, String department) {
//        return employees.stream().filter(employee -> employee.getDepartment().equals(department)).collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
//    }
//
//    // 11 : What is the average salary of male and female employees?
//    public static Map<String, Double> getAverageSalaryByGender(List<Employee> employees) {
//        return employees.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(employee -> employee.getSalary().doubleValue())));
//    }
//
//    // 12 : List down the names of all employees in each department?
//    public static Map<String, List<String>> getNameListByDept(List<Employee> employees) {
//        Map<String, List<String>> resultMap = new HashMap<>();
//        Map<String, List<Employee>> deptList = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));
//
//    deptList.entrySet().forEach(entry -> {
//            resultMap.put(entry.getKey(), entry.getValue().stream().map(Employee::getName).collect(Collectors.toList()));
//        });*//*
//
//        deptList.forEach((key, value) -> resultMap.put(key, value.stream().map(Employee::getName).collect(Collectors.toList())));
//        return resultMap;
//    }
//
//    // 13 : What is the average salary and total salary of the whole organization?
//    public static void findSalaryStatics(List<Employee> employees) {
//        //return employees.stream().mapToDouble(employee -> employee.getSalary().doubleValue()).average().getAsDouble();
//        DoubleSummaryStatistics employeeSalaryStatistics = employees.stream().collect(Collectors.summarizingDouble(emp -> emp.getSalary().doubleValue()));
//        log.info("Min Salary: {}", employeeSalaryStatistics.getMin());
//        log.info("Max Salary: {}", employeeSalaryStatistics.getMax());
//        log.info("Average Salary: {}", employeeSalaryStatistics.getAverage());
//        log.info("Sum Salary: {}", employeeSalaryStatistics.getSum());
//    }
//
//    //14 : Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.
//    public static void separateYoungEmployees(List<Employee> employees) {
//        Map<Boolean, List<Employee>> partitionEmployeesByAge = employees.stream().collect(Collectors.partitioningBy(e -> e.getAge() > 25));
//        Set<Map.Entry<Boolean, List<Employee>>> entrySet = partitionEmployeesByAge.entrySet();
//
//        for (Map.Entry<Boolean, List<Employee>> entry : entrySet) {
//            log.info("----------------------------");
//
//            if (entry.getKey()) {
//                log.info("Employees older than 25 years :");
//            } else {
//                log.info("Employees younger than or equal to 25 years :");
//            }
//
//            log.info("----------------------------");
//
//            List<Employee> list = entry.getValue();
//
//            for (Employee e : list) {
//                log.info(e.getName());
//            }
//        }
//    }
//
//    //15 : Who is the oldest employee in the organization? What is his age and which department he belongs to?
//    public static Optional<Employee> findOldestEmp(List<Employee> employees) {
//        return employees.stream().max(Comparator.comparing(Employee::getAge));
//    }
//
//    public static Map<Integer, BigDecimal> mapEmpSalary(List<Employee> employees){
//        return employees.stream().collect(Collectors.toMap(Employee::getId , Employee::getSalary));
//    }
//
//    */
///**
//     * Stream Operations on String problem solving
//     **/
//
//    // 1. Reverse words in String
//    public static String reverseWords(String strNames) {
//
//
//        StringBuilder reverseNames = new StringBuilder();
//        String[] namesArray = strNames.split(" ");
//        for (int i = namesArray.length; i >= 1; i--) {
//            reverseNames.append(" ").append(namesArray[i - 1]);
//        }
//        return reverseNames.toString();
//
//
//        String[] namesArray = strNames.split(" ");
//        return IntStream.iterate(namesArray.length, i -> i >= 1, i -> i - 1).mapToObj(i -> " " + namesArray[i - 1]).collect(Collectors.joining());
//    }
//
//    public static String addCharToString(String string, char letter, int pos) {
//        // Using substring
//        return string.substring(0, pos) + letter + string.substring(pos);
//        // Using StringBuilder
//        //return new StringBuilder(string).insert(pos,letter).toString();
//    }
//
//    // convert enum to String
//
//
//    public static Float convertToFloat(String string) {
//        //return Float.valueOf(string);
//        return Float.parseFloat(string);
//    }
//
//    public static String interpolation(String string, String field) {
//        return String.format("%s is the best platform to learn %s .", string, field);
//    }
//
//    public static String printSeparateLines(String one, String two) {
//        //return one + System.lineSeparator() + two;
//        return one + "\n" + two;
//    }
//    public static boolean isHavingSpecialChars(String string){
//        Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(string);
//        return matcher.find();
//    }
//    */
///** Asked in Mphasis**//*
//
//    public static void findMostDuplicate(List<Integer> numbers) {
//        Map<Integer, Long> countMap = numbers.stream().collect(Collectors.groupingBy(Math::abs, Collectors.counting()));
//        log.info("count Map: {}", countMap);
//        Map.Entry<Integer, Long> maxMap = countMap.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get();
//        log.info("Max Value Map: {}", maxMap);
//        log.info("Max Count value: {}", countMap.values().stream().mapToInt(Math::toIntExact).max().getAsInt());
//    }
//
//
//}
//
