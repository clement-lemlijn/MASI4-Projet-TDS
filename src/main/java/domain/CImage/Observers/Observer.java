package domain.CImage.Observers;

import CImage.*;
import domain.CImage.CImage;

public interface Observer 
{
    public void   setCImage(CImage ci);
    public CImage getCImage();
    public void   update();
}
