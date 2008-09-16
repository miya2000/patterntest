package visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * VisitorTest
 * (test for http://www.aerith.net/design/Visitor-j.html )
 * 
 * @author miya2000
 */
public class VisitorTest {
	public static void main(String[] args) {
		// ÉfÅ[É^çÏê¨
		DirectoryEntry root = new DirectoryEntry("root");
		root.appendChild(new FileEntry("file1"))
			.appendChild(new FileEntry("file2"))
			.appendChild(new FileEntry("file3"))
			.appendChild(new DirectoryEntry("dir1")
				.appendChild(new FileEntry("dir1-file1"))
				.appendChild(new FileEntry("dir1-file2"))
				.appendChild(new FileEntry("dir1-file3"))
				.appendChild(new DirectoryEntry("dir1-dir2"))
				.appendChild(new DirectoryEntry("dir1-dir3")
					.appendChild(new FileEntry("dir1-dir3-file1"))
					.appendChild(new FileEntry("dir1-dir-3file2"))
					.appendChild(new FileEntry("dir1-dir3-file3")))
			.appendChild(new FileEntry("file4"))
			.appendChild(new FileEntry("file5"))
		);
		// visitor Ç…ÇÊÇÈèàóù
		EntryVisitor visitor = new EntryVisitor();
		root.accept(visitor);
	}
}

interface Entry {
	public void accept(EntryVisitor v);		
}
class FileEntry implements Entry {
	private String name;
	public FileEntry(String name){
		this.name = name;
	}
	@Override
	public void accept(EntryVisitor v){
		v.visit(this);
	}
	public String getName(){
		return name;
	}
}
class DirectoryEntry implements Entry {
	private String title;
	private List<Entry> childs;
	public DirectoryEntry(String title){
		this.title = title;
		this.childs = new ArrayList<Entry>();
	}
	public void accept(EntryVisitor v){
		v.visit(this);
	}
	public String getTitle(){
		return title;
	}
	public DirectoryEntry appendChild(Entry e){
		childs.add(e);
		return this;
	}
	public List<Entry> getChilds() {
		return childs;
	}
}

class EntryVisitor {
	public void visit(FileEntry a) {
		System.out.println(a.getName());
	}
	public void visit(DirectoryEntry a) {
		System.out.println(a.getTitle());
		for (Entry e : a.getChilds()){
			e.accept(this);
		}
	}
}

