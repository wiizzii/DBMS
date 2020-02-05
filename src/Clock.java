package bufmgr;

public class Clock extends Replacer{
	//to identify different states of a page
	static int freeState = 1, pinned = 2, unPinned = 3;

	protected Clock(BufMgr bufmgr) {
		super(bufmgr);
		for (FrameDesc f : frametab){
			f.state = freeState;
		}
	}

	@Override
	public void newPage(FrameDesc fdesc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void freePage(FrameDesc fdesc) {
		//free the state of the page
		fdesc.state = freeState;
	}

	@Override
	public void pinPage(FrameDesc fdesc) {
		//set the state of the page to pinned
 		fdesc.state = pinned;
	}

	@Override
	public void unpinPage(FrameDesc fdesc) {
		// sets the state of the page to unPinned
		if (fdesc.pincnt == 0) {
			fdesc.state = unPinned;
		}
	}
	int ptr=0;
	@Override
	public int pickVictim() {
		/*run through every frame and if one frame isn't pinned
		*its state is set to free and if the function finds a free frame it is returned.
		*the list is sun through 2 times to be sure that if a frame was found that wasn't
		*pinned it is not freed while still being used*/
		//int ptr=0;
		for(int j=0;j<2;j++){
			for ( int i = 0 ; i < frametab.length; i++ ) {

					FrameDesc pageInfo = frametab[ptr];
					if(unPinned == pageInfo.state){
						pageInfo.state = freeState;
					}else if(freeState == pageInfo.state){
						return ptr;
					}

					ptr = (ptr+1) % frametab.length;
				}
		}
		/*for (FrameDesc f : frametab){
			if (f.state == unPinned) {
				f.state = freeState;
			}else if (f.state == freeState){
				return ptr;
			}
			ptr++;
		}
		ptr = 0;
		for (FrameDesc f : frametab){
			if (f.state == unPinned) {
				f.state = freeState;
			}else if (f.state == freeState){
				return ptr;
			}
			ptr++;
		}
		// if no page was found with 0 pins error is returned
		return -1;
	}*/
	/*for ( int i = 0 ; i < frametab.length << 1; i++ ) {

			final FrameDesc fd = frametab[ptr];

			if(freeState == fd.state){
				return ptr;
			}
			if(unPinned == fd.state){
				fd.state = freeState;
			}
			ptr = (ptr+1) % frametab.length;
		}*/
		return -1;
	}

}
