package testConcurrent.num7Atomic;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {
	public static AtomicReference<User> atomicUserReference = new AtomicReference<>();

	public static void main(String[] args) {
		User user = new User("conan",15);
		atomicUserReference.set(user);
		User updateUser = new User("Shinichi",17);
		//user.setName("123");
		System.out.println(atomicUserReference.get().getName());
		System.out.println(atomicUserReference.get().getOld());
		atomicUserReference.compareAndSet(user,updateUser);
		System.out.println(atomicUserReference.get().getName());
		System.out.println(atomicUserReference.get().getOld());

	}



	static class User{
		private String name;
		private int old;
		public User(String name,int old){
			this.name = name;
			this.old = old;
		}
		public String getName(){
			return name;
		}
		public int getOld(){
			return old;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setOld(int old) {
			this.old = old;
		}
	}
}
