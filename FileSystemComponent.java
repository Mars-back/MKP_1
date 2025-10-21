public interface FileSystemComponent
{
    void display(String indent);
    long getSize();
    String getName();
    void rename(String newName);
}