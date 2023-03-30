package org.apache.commons.mail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.mail.Authenticator;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;

import org.junit.Before;
import org.junit.Test;

public class EmailTest {

	private Email email;
	
@Before
public void setup() {
	email = new EmailDummy();	// setup for email dummy
}

@Test
public void addCcTest() {				
	
	try {
		email.addCc("test@Name");	// add the email: test@Name to test
	} catch (EmailException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	List<InternetAddress> cc = email.getCcAddresses();	// store into a list called cc of InternetAddresses
	assertEquals("test@Name", cc.get(0).toString());	// check to see if what was stored is the same as the created email
}

@Test
public void addBccArrayTest() {				
	String [] emailArray = {"test@Name1", "test@Name2", "test@Name3"};	// create an array of emails with three different emails
	
	try {
		email.addBcc(emailArray);	// add the email array to the Bcc list
	} catch (EmailException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	List<InternetAddress> Bcc = email.getBccAddresses();	// put the emails into the address list
	assertEquals("test@Name2", Bcc.get(1).toString());	// check to see if it can correctly return one of the emails
}

@Test
public void addHeaderNullTest()	{	
		
	email.addHeader("testName", "12");	// pass in strings for name and value

	assertEquals(null, email.headers.get("12"));	// expecting to get a null in return 
}

@Test(expected = IllegalArgumentException.class)	// will be expecting the exception IllegalArgumentException
public void addHeaderExceptionTest()	{	
		
	email.addHeader("", "");	// pass in empty name and value to return wanted exception
	
	// assertion is replaced with the expected exception
}

@Test
public void addReplyToTest(){	
	
	try {
		email.addReplyTo("test@Name", "testName");	// add the email: test@Name with name: testName to the addReply
	} catch (EmailException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	assertEquals("test@Name", email.getReplyToAddresses().get(0).getAddress());	// expecting to get the correct returned email 
}

@Test
public void getHostNameTest() {					
	email.setHostName("testName");	// set the test name to: testName
				
	assertEquals("testName", email.getHostName());	// check to see if the name that was given is the same that's returned
}

@Test
public void getHostNameNullTest() {					
	email.setHostName("");	// set the test name to: testName
				
	assertEquals(null, email.getHostName());	// check to see if the name that was given is the same that's returned
}

@Test
public void getMailSessionTest() {					
	email.hostName = "testName";
	
	try {
		assertTrue(email.getMailSession() != null);	// ensures there is a valid mail session
		} catch (EmailException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

@Test
public void getMailSessionChangeAttributesTest() {				
	Authenticator auth = new AuthenticatorDummy();	// create a dummy for authenticator
	
	email.setHostName("name");
	
	// set the specific value to go down the different paths
	email.socketConnectionTimeout = -3;
	email.socketTimeout = -3;
	email.authenticator = null;
	email.setSSLOnConnect(false);
	email.setBounceAddress("bounce@address");
	
	email.setAuthenticator(auth);	// set the dummy authenticator
	
	try {
		assertTrue(email.getMailSession() != null);		
		} catch (EmailException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

@Test
public void getSentDateNullTest() {						
	email.setSentDate(null);	// set a null date
	assertTrue(email.getSentDate() != null);	// ensures there will still be a valid date returned
}

@Test
public void getSentDateValidTest() {					
	
	email.sentDate = email.getSentDate();
	
	email.setSentDate(email.sentDate);	// pass in the date
	
	assertEquals(email.sentDate, email.getSentDate());	// checks to see if the two dates are the same
}

@Test
public void getSocketConnectionTimeoutTest() {							
	email.setSocketConnectionTimeout(12);	// pass in the wanted time
	
	assertEquals(12, email.getSocketConnectionTimeout());	// checks to see if the two times are the same
}

@Test
public void setFromTest(){	
	try {
		email.setFrom("test@email");	// set the wanted from email 
	} catch (EmailException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}		
	assertEquals("test@email", email.getFromAddress().getAddress());	// check to see if the correct from email was set 
}

@Test
public void BuildMimeMessageWithListsTest()	{	
	
	email.setHostName("host@email");	// set host name
	
	try {
		email.setFrom("from@email");
	} catch (EmailException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		email.addTo("test@Name");
	} catch (EmailException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}	
	
	List<InternetAddress> addressToList = email.getToAddresses();	// store into a list for To of InternetAddresses

	try {
		email.setTo(addressToList);
	} catch (EmailException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		email.addCc("testCc@email");
	} catch (EmailException e3) {
		// TODO Auto-generated catch block
		e3.printStackTrace();
	}
	List<InternetAddress> addressCcList = email.getCcAddresses();	// store into a list for Cc of InternetAddresses

	try {
		email.setCc(addressCcList);
	} catch (EmailException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		email.addBcc("testBcc@email");
	} catch (EmailException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	List<InternetAddress> addressBccList = email.getBccAddresses();	// store into a list for Bcc of InternetAddresses
	
	try {
		email.setBcc(addressBccList);
	} catch (EmailException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		email.addReplyTo("testReplyTo@email");
	} catch (EmailException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	List<InternetAddress> addressReplyToList = email.getReplyToAddresses();	// store into a list for ReplyTo of InternetAddresses
	
	try {
		email.setReplyTo(addressReplyToList);
	} catch (EmailException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	email.setSubject("subject");	// sets the subject
	
    MimeMultipart mimer = new MimeMultipart();	// creates a multipart called mimer to be passed in for mimer and body

    // passes in the multipart object
	email.setContent(mimer);	
	email.emailBody = mimer;	

	email.addHeader("name", "size");	// header with the two strings passed in

	email.setSentDate(null);	// make date null

	try {
		email.buildMimeMessage();	// run the void method with the given information
	} catch (EmailException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

@Test
public void BuildMimeMessageNoCcsNorReplyToNullMimerTest()	{	
	
	email.setHostName("host@email");

	try {
		email.setFrom("from@email");
	} catch (EmailException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		email.addTo("test@Name");
	} catch (EmailException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}	
		
	email.setSubject("Subject");	// subject to set is called subject
	
    MimeMultipart mimer = null;	// want the multipart to be null this time

    Object obj = new Object();
    email.setContent(obj, "testSubject");	// pass in the params
    
	email.setContent(mimer);	
	email.emailBody = mimer;	
	
	email.addHeader("name", "12");
	
	email.setSentDate(null);
		
	try {
		email.buildMimeMessage();
	} catch (EmailException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


@Test
public void BuildMimeMessageContentTypeNullTest()	{	
	
	email.setHostName("host@email");

	try {
		email.setFrom("from@email");
	} catch (EmailException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		email.addTo("test@Name");
	} catch (EmailException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}	
	
	email.setSubject("Subject");
	
    MimeMultipart mimer = null;
	
    Object obj = null;
	
    email.setContent(obj,"");	// pass in a null and empty string
	
	email.setContent(mimer);	
	email.emailBody = mimer;	
	
	email.addHeader("name", "12");
	
	email.setSentDate(null);
	
	try {
		email.buildMimeMessage();
	} catch (EmailException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}