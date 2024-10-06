// This class represents an integer "Node" for use in an LinkedIntList
// with a method for determining if two Node are equal
class Node {
   public int data;
   public Node next;

   Node(int data) {
      this.data = data;
   }

   Node(int data, Node next) {
      this.data = data;
      this.next = next;
   }

   // Pre: constructor has been called
   // Post: a boolean representing if two Nodes are the same
   public boolean equals(Object obj) {
      // return true if the given object has the same instance value
      if (this == obj)
         return true;
      // return false if the given object is null
      if (obj == null)
         return false;
      // return false if the class name of the instance and the given object are different
      if (getClass() != obj.getClass())
         return false;
      // cast obj as Node
      Node n = (Node) obj;
      return this.data == n.data && this.next == n.next;
   }
}