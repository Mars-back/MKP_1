import java.util.ArrayList;
import java.util.List;

public class Folder implements FileSystemComponent
{
    private String name;
    private List<FileSystemComponent> children = new ArrayList<>();

    public Folder(String name)
    {
        this.name = name;
    }

    public void addComponent(FileSystemComponent component)
    {
        children.add(component);
    }

    public void removeComponent(FileSystemComponent component)
    {
        children.remove(component);
    }

    public FileSystemComponent findChild(String name)
    {
        for (FileSystemComponent child : children)
        {
            if (child.getName().equals(name))
            {
                return child;
            }
        }
        return null;
    }

    @Override
    public void display(String indent)
    {
        System.out.println(indent + "+ " + this.name);
        for (FileSystemComponent component : children)
        {
            component.display(indent + "  ");
        }
    }

    @Override
    public long getSize()
    {
        long totalSize = 0;
        for (FileSystemComponent component : children)
        {
            totalSize += component.getSize();
        }
        return totalSize;
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