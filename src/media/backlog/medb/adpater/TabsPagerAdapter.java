package media.backlog.medb.adpater;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 
public class TabsPagerAdapter extends FragmentPagerAdapter {
 
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // Movies fragment activity
            return new OrgMoviesFragment();
        case 1:
            // Games fragment activity
            return new OrgGamesFragment();
        case 2:
            // Books fragment activity
            return new OrgBooksFragment();
        case 3:
            // Music fragment activity
        	return new OrgMusicFragment();
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 4;
    }
 
}