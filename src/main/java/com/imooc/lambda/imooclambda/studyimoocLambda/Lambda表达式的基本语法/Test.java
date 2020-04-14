public class Test{
	public static void main(String[] args){
		IMarkUp imu = (message) -> System.out.println(message);
		imu.markUp("Lambda");

		/**
			new 方法引用Test&Lambda$1().markUp("Lambda");
		 */
	}

	/**
	 private static void lambda$main$0(java.lang.String message){
	 	System.out.println(message);
	 }
	 */

	/**
	 final class 方法引用Test$$Lambda$1 implements IMarkUp {
		 private 方法引用Test$$Lambda$1();
		 public void markUp(java.lang.String msg){
	 		方法引用Test.lambda$main$0(msg);
	 	}
	 }
	 */
}
interface IMarkUp{
	void markUp(String message);
}