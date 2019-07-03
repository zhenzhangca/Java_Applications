package ca.jrvs.apps.jdbc;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JDBCExecutor {
    public static void main(String[] args) {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                "hplussport","postgres", "password");

        try{
            Connection connection = dcm.getConnection();

            CustomerDAO customerDAO = new CustomerDAO(connection);
            //Demo 1, test connection
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM CUSTOMER");
            while(resultSet.next()){
                System.out.println(resultSet.getInt(1));
            }
            System.out.println("===========DEMO 1==============");

            //Demo 2, test create method in CustomerDAO
            Customer customer_2 = new Customer();
            customer_2.setFirstName("Zhen");
            customer_2.setLastName("Zhang");
            customer_2.setEmail("zhenzhang@123.com");
            customer_2.setPhone("123-456-7890");
            customer_2.setAddress("123 Bloor St.");
            customer_2.setCity("Toronto");
            customer_2.setState("ON");
            customer_2.setZipCode("M3K5R1");
            //have return value, test when necessary
            customerDAO.create(customer_2);
            System.out.println("===========DEMO 2==============");

            //Demo 3 test findById method in CustomerDAO
            Customer customer_3 =customerDAO.findById(10007);
            System.out.println(customer_3.getFirstName()+", " + customer_3.getLastName());
            System.out.println("===========DEMO 3==============");

            //Demo 4 test update method in CustomerDAO
            Customer customer_4 = customerDAO.findById(10007);
            System.out.println(customer_4.getFirstName() + ", " + customer_4.getLastName() + " " + customer_4.getEmail());
            customer_4.setEmail("zhenzhang@wh.gov");
            //System.out.println(customer_4.getFirstName() + ", " + customer_4.getLastName() + " " + customer_4.getEmail());
            customer_4 = customerDAO.update(customer_4);
            System.out.println(customer_4.getFirstName() + ", " + customer_4.getLastName() + " " + customer_4.getEmail());
            System.out.println("===========DEMO 4==============");

            //Demo 5 test delete method in CustomerDAO
            Customer customer_5 = customerDAO.findById(10007);
            System.out.println(customer_5);
            customerDAO.delete(customer_5.getId());
            System.out.println(customerDAO.findById(10007  ));
            System.out.println("===========DEMO 5==============");

            OrderDAO orderDAO = new OrderDAO(connection);
            //Demo 6 test findById method in OrderDAO
            Order order = orderDAO.findById(1000);
            System.out.println(order);
            System.out.println("===========DEMO 6==============");

            //Demo 7 test getOrderForCustomer method in OrderDAO
            List<Order> orders = orderDAO.getOrderForCustomer(789);
            orders.forEach(System.out::println);
            System.out.println("===========DEMO 7==============");

            //Demo 8 test findAllSorted method in CustomerDAO
            List<Customer> customers = customerDAO.findAllSorted(20);
            customers.forEach(System.out::println);
            System.out.println("===========DEMO 8==============");

            //Demo 9 test findAllPaged
            System.out.println("FindAllPaged:");
            for(int i =1; i < 3; i++) {
                System.out.println("********Page number:" + i);
                customerDAO.findAllPaged(10, i).forEach(System.out::println);
            }
            System.out.println("===========DEMO 9==============");
        }catch(SQLException e){
            e.printStackTrace();
        }








    }
}

