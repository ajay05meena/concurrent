
public class ThreadSignal {
		protected boolean hasDataToProcess = false;
		public synchronized boolean hasDataToProcess(){
			return hasDataToProcess;
		}
		public synchronized void setHasDataToProcess(boolean hasData){
			this.hasDataToProcess=hasData;
		}
		
		
}
