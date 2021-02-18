package pageObjects;

public interface LoginP 
{
  //login page	
  public static String yurl="https://login.yahoo.com/?.src=ym&.lang=en-US&.intl=us&.done=https%3A%2F%2Fmail.yahoo.com%2Fd";	
  public static String nemail="username";
  public static String nnext="signin";
  public static String npwd="password";
  public static String nnext2="verifyPassword";
  public static String xerrmsg="//p[@class='error-msg']";
  public static String isignup="createacc";
  
  //registration page
  public static String nfname="firstName";
  
  //inbox page
  public static String lsignout="Sign Out";
  
}
