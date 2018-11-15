public class spamObject
{
   private int time;
   private String spam;
   
   public spamObject()
   {
      time = 0;
      spam = "";
   }
   public spamObject(int numOfEnter, String text)
   {
      time = numOfEnter;
      spam = text;
   }
   public int getTime()
   {
      return time;
   }
   public String getSpam()
   {
      return spam;
   }
   public void setSpam(String text)
   {
      spam = text;
   }
   public void setTime(int numOfEnter)
   {
      time = numOfEnter;
   }
}