 

import java.io.File;

public class FileSystemTree {
    LinkedBinaryTree<String> fileSys;
    
    protected static LinkedBinaryTree<String> buildTree(File root) {
        //TODO: create an entry of LinkedBinaryTree<String> instance;
        //      add root.getName() to the entry
        LinkedBinaryTree<String> entry = new LinkedBinaryTree<String>();
        entry.addRoot(root.getName());
        
        if(root.isDirectory()) {
            LinkedBinaryTree<String> folder = null;
            Position<String> pos = null;
            for(String childName: root.list()) {
                //TODO: create child, a File instance, using root and childName;
                //      create a childEntry, a LinkedBinaryTree instance, by calling buildTree;
                File child = new File(root, childName);
                LinkedBinaryTree<String> childEntry = buildTree(child);
                
                if(folder == null) {
                    //TODO: copy childEntry to folder
                    //      set pos to the root of folder
                    folder = childEntry;
                    pos = folder.root();
                }
                else {
                    //TODO: attach childEntry to the right link of pos
                    //      move pos to the right child of pos
                    folder.attach(pos, null, childEntry);
                    pos = folder.right(pos);
                    
                }
            }
            
            //TODO: attach folder to the left link of entry
            entry.attach(entry.root(), folder, null);
        }
        return entry;
    }
    
    public static LinkedBinaryTree<String> build(String root) {
        return buildTree(new File(root));
    }

    public static void printTree(BinaryTree<String> tree, Position<String> pos, int depth) {
        for(int i = 0; i < depth; i++)
            System.out.print("    ");
        //TODO: print the name at pos;
        //      if pos is a directory, visit all entries in the directory with an increased depth;
        //      if more entries are in the currenty directory visit the remaining entries;
        System.out.println(pos.getElement()); 
        if(tree.left(pos) != null){
            printTree(tree, tree.left(pos), depth+1);
        }
        if(tree.right(pos) != null){
            printTree(tree, tree.right(pos), depth);
        }
    }
    
    public static void main(String[] args) {
        LinkedBinaryTree<String> tree = build("C:\\Users\\youngmin.kwon\\tmp");
        //Initially check if the tree is built correctly
        for(String name : tree)
            System.out.println(name);
        
        //Print the structure with indentations
        printTree(tree, tree.root(), 0);
    }
}
