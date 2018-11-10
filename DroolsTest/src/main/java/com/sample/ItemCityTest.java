package com.sample;


import java.math.BigDecimal;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;

import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;

import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

import com.sample.ItemCity.City;
import com.sample.ItemCity.Type;

/* This is a sample class to launch a rule. */

public class ItemCityTest {
   public static final void main(String[] args) {
      try {
    	  
    	  System.out.println("Inside ItemCityText Main Fuction");
         // load up the knowledge base
         KnowledgeBase kbase = readKnowledgeBase();
         StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
         
         ItemCity item1 = new ItemCity();
         item1.setPurchaseCity(City.PUNE);
         item1.setTypeofItem(Type.MEDICINES);
         item1.setSellPrice(new BigDecimal(10));
         ksession.insert(item1);
         System.out.println("Item1 Created");
         
         ItemCity item2 = new ItemCity();
         item2.setPurchaseCity(City.PUNE);
         item2.setTypeofItem(Type.GROCERIES);
         item2.setSellPrice(new BigDecimal(10));
         ksession.insert(item2);
         System.out.println("Item2 Created");
         
         ItemCity item3 = new ItemCity();
         item3.setPurchaseCity(City.NAGPUR);
         item3.setTypeofItem(Type.MEDICINES);
         item3.setSellPrice(new BigDecimal(10));
         ksession.insert(item3);
         System.out.println("Item3 Created");
         
         ItemCity item4 = new ItemCity();
         item4.setPurchaseCity(City.NAGPUR);
         item4.setTypeofItem(Type.GROCERIES);
         item4.setSellPrice(new BigDecimal(10));         
         ksession.insert(item4);
         System.out.println("Item4 Created");
         
         ksession.fireAllRules();
         
         System.out.println("For Item1 = "+item1.getPurchaseCity().toString() + " " 
            + item1.getLocalTax().intValue());
         
         System.out.println("For Item2 = "+item2.getPurchaseCity().toString() + " "
            + item2.getLocalTax().intValue());
         
         System.out.println("For Item3 = "+item3.getPurchaseCity().toString() + " "
            + item3.getLocalTax().intValue());
         
         System.out.println("For Item4 = "+item4.getPurchaseCity().toString() + " "
            + item4.getLocalTax().intValue());
                            
      } catch (Throwable t) {
         t.printStackTrace();
      }
   }
   
   private static KnowledgeBase readKnowledgeBase() throws Exception {
   
	   System.out.println("Inside Read Knowledge Base");
      KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
      
      kbuilder.add(ResourceFactory.newClassPathResource("Pune.drl"), ResourceType.DRL);
      kbuilder.add(ResourceFactory.newClassPathResource("Nagpur.drl"), ResourceType.DRL);
      
      KnowledgeBuilderErrors errors = kbuilder.getErrors();
      
      if (errors.size() > 0) {
         for (KnowledgeBuilderError error: errors) {
            System.err.println(error);
         }
         throw new IllegalArgumentException("Could not parse knowledge.");
      }
      
      KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
      kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
      
      return kbase;
   }
}
