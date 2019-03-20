

/**
  * created by chandrashekhar 2019-03-11 
  **/

case class IntNode(value: Int, left: Option[IntNode] = None, right: Option[IntNode] = None) {

  def preorder(f: IntNode => Unit) {
    print(value)
    left.map(_.preorder(f))
    right.map(_.preorder(f))
  }

  //124753689
  def postorder(f: IntNode => Unit) {
    left.map(_.postorder(f))
    right.map(_.postorder(f))
    f(this)
    print(value)
  }

  def inorder(f: IntNode => Unit) {
    left.map(_.inorder(f))
    print(value)
    f(this)
    right.map(_.inorder(f))
  }

  def levelorder(f: IntNode => Unit) {

    def loVisit(ls: List[IntNode]): Unit = ls match {
      case Nil => None
      case node :: rest => f(node); loVisit(rest ++ node.left ++ node.right)
    }

    loVisit(List(this))
  }
}

object TreeTraversal extends App {
  implicit def intNode2SomeIntNode(n: IntNode) = Some[IntNode](n)

  val tree = IntNode(1,
    IntNode(2,
      IntNode(4,
        IntNode(7)),
      IntNode(5)),
    IntNode(3,
      IntNode(6,
        IntNode(8),
        IntNode(9))))

  tree.preorder(x => x)
  //  println("+++++++++++++++++")
  //  tree.postorder(x => x)
  //  println("+++++++++++++++++")
  //  tree.inorder(x => x)

}
