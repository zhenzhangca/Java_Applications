package ca.jrvs.apps.twitter.example;

import ca.jrvs.apps.twitter.example.dto.Company;


public class TestDemo {
    public static void main(String[] args) {
        String json = "{\"ceo\":\"jrvs\", \"symbol\":\"AAPL\", \"companyName\":\"apple inc\", \"exchange\":\"djongohodu\",\"description\":\"good good\"}";

        Company c = new Company();
        c.setCEO("json");
        c.setCompanyName("jrvs");
        c.setDescription("good good study, day day up");
        boolean prettyJson = true;
        boolean includeEmptyValues = true;



        JsonParser jp = new JsonParser();
        try{
            String result = jp.toJson(c, prettyJson, includeEmptyValues);
            System.out.println(result);
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            Company company = (Company)jp.toObjectFromJson(json.toString(),  Company.class);
            System.out.println(company.getCEO()+"  "+company.getCompanyName()+" "+company.getDescription());
        }catch (Exception e){
            e.printStackTrace();
        }






    }
}
