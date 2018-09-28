package RestAPI.users;


public class PostBody{
  
  String title; 
  String content;
  String status ;
  
  public PostBody() {
    // TODO Auto-generated constructor stub
  }

  
  public PostBody(String title, String content, String status) {
    super();
    this.title = title;
    this.content = content;
    this.status = status;
  }

  @Override
  public String toString() {
    return "PostBody [title=" + title + ", content=" + content + ", status=" + status + "]";
  }
}