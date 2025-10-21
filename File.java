public class File implements FileSystemComponent
{
    private String name;
    private long size;

    public File(String name, long size)
    {
        this.name = name;
        this.size = size;
    }

    @Override
    public void display(String indent)
    {
        System.out.println(indent + "- " + this.name + " (" + this.size + " bytes)");
    }

    @Override
    public long getSize()
    {
        return this.size;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public void rename(String newName)
    {
        this.name = newName;
    }
}