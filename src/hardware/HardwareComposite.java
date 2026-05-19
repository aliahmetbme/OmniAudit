package hardware;

import tasks.SystemTaskVisitor;
import java.util.ArrayList;
import java.util.List;

/*
* İşte Composite kalıbının sihrinin gerçekleştiği yer! İçinde başka donanım parçaları barındıran (Anakart gibi) sınıfları gruplamak için bir HardwareComposite soyut sınıfı yazıyoruz.
* */
//Şimdi projedeki gerçek gruplarımızı (Bilgisayar, Anakart, ISA Veriyolu) bu sınıftan türetiyoruz. İçleri şimdilik boş görünebilir çünkü tüm ekleme/çıkarma (add/remove) ve kabul etme (accept) yeteneklerini zaten üst sınıftan (HardwareComposite) miras aldılar!
public abstract class HardwareComposite implements HardwareComponent {

    // Holds the child components (Leaf or other Composites)
    protected List<HardwareComponent> children = new ArrayList<>();

    public void addComponent(HardwareComponent component) {
        children.add(component);
    }

    public void removeComponent(HardwareComponent component) {
        children.remove(component);
    }

    @Override
    public void accept(SystemTaskVisitor visitor) {
        // Iterate through all children and pass the visitor to them
        for (HardwareComponent child : children) {
            child.accept(visitor);
        }
    }

    @Override
    public void getMetrics() {
        for (HardwareComponent child : children) {
            child.getMetrics();
        }
    }
}