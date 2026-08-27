package net.dingdingduang.somebasicskills.networking;

import com.mojang.datafixers.util.Function10;
import com.mojang.datafixers.util.Function11;
import net.minecraft.network.codec.StreamCodec;

import java.util.function.Function;

public class StreamCodecMoreMethods {
    public static <B, C> StreamCodec<B, C> composite(final Function0<C> factory) {
        return new StreamCodec<B, C>() {
            public C decode(B byteData) {
                return (C) factory.apply();
            }

            @Override
            public void encode(B byteData, C decipherFunc) {

            }
        };
    }

    public static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> StreamCodec<B, C> composite(final StreamCodec<? super B, T1> codec1, final Function<C, T1> getter1, final StreamCodec<? super B, T2> codec2, final Function<C, T2> getter2, final StreamCodec<? super B, T3> codec3, final Function<C, T3> getter3, final StreamCodec<? super B, T4> codec4, final Function<C, T4> getter4, final StreamCodec<? super B, T5> codec5, final Function<C, T5> getter5, final StreamCodec<? super B, T6> codec6, final Function<C, T6> getter6, final StreamCodec<? super B, T7> codec7, final Function<C, T7> getter7, final StreamCodec<? super B, T8> codec8, final Function<C, T8> getter8,  final StreamCodec<? super B, T9> codec9, final Function<C, T9> getter9,  final StreamCodec<? super B, T10> codec10, final Function<C, T10> getter10, final Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, C> factory) {
        return new StreamCodec<B, C>() {
            public C decode(B byteData) {
                T1 t1 = (T1)codec1.decode(byteData);
                T2 t2 = (T2)codec2.decode(byteData);
                T3 t3 = (T3)codec3.decode(byteData);
                T4 t4 = (T4)codec4.decode(byteData);
                T5 t5 = (T5)codec5.decode(byteData);
                T6 t6 = (T6)codec6.decode(byteData);
                T7 t7 = (T7)codec7.decode(byteData);
                T8 t8 = (T8)codec8.decode(byteData);
                T9 t9 = (T9) codec9.decode(byteData);
                T10 t10 = (T10) codec10.decode(byteData);
                return (C)factory.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10);
            }

            public void encode(B byteData, C decipherFunc) {
                codec1.encode(byteData, getter1.apply(decipherFunc));
                codec2.encode(byteData, getter2.apply(decipherFunc));
                codec3.encode(byteData, getter3.apply(decipherFunc));
                codec4.encode(byteData, getter4.apply(decipherFunc));
                codec5.encode(byteData, getter5.apply(decipherFunc));
                codec6.encode(byteData, getter6.apply(decipherFunc));
                codec7.encode(byteData, getter7.apply(decipherFunc));
                codec8.encode(byteData, getter8.apply(decipherFunc));
                codec9.encode(byteData, getter9.apply(decipherFunc));
                codec10.encode(byteData, getter10.apply(decipherFunc));
            }
        };
    }

    public static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> StreamCodec<B, C> composite(final StreamCodec<? super B, T1> codec1, final Function<C, T1> getter1, final StreamCodec<? super B, T2> codec2, final Function<C, T2> getter2, final StreamCodec<? super B, T3> codec3, final Function<C, T3> getter3, final StreamCodec<? super B, T4> codec4, final Function<C, T4> getter4, final StreamCodec<? super B, T5> codec5, final Function<C, T5> getter5, final StreamCodec<? super B, T6> codec6, final Function<C, T6> getter6, final StreamCodec<? super B, T7> codec7, final Function<C, T7> getter7, final StreamCodec<? super B, T8> codec8, final Function<C, T8> getter8,  final StreamCodec<? super B, T9> codec9, final Function<C, T9> getter9, final StreamCodec<? super B, T10> codec10, final Function<C, T10> getter10, final StreamCodec<? super B, T11> codec11, final Function<C, T11> getter11, final Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, C> factory) {
        return new StreamCodec<B, C>() {
            public C decode(B byteData) {
                T1 t1 = (T1)codec1.decode(byteData);
                T2 t2 = (T2)codec2.decode(byteData);
                T3 t3 = (T3)codec3.decode(byteData);
                T4 t4 = (T4)codec4.decode(byteData);
                T5 t5 = (T5)codec5.decode(byteData);
                T6 t6 = (T6)codec6.decode(byteData);
                T7 t7 = (T7)codec7.decode(byteData);
                T8 t8 = (T8)codec8.decode(byteData);
                T9 t9 = (T9) codec9.decode(byteData);
                T10 t10 = (T10) codec10.decode(byteData);
                T11 t11 = (T11) codec11.decode(byteData);
                return (C)factory.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11);
            }

            public void encode(B byteData, C decipherFunc) {
                codec1.encode(byteData, getter1.apply(decipherFunc));
                codec2.encode(byteData, getter2.apply(decipherFunc));
                codec3.encode(byteData, getter3.apply(decipherFunc));
                codec4.encode(byteData, getter4.apply(decipherFunc));
                codec5.encode(byteData, getter5.apply(decipherFunc));
                codec6.encode(byteData, getter6.apply(decipherFunc));
                codec7.encode(byteData, getter7.apply(decipherFunc));
                codec8.encode(byteData, getter8.apply(decipherFunc));
                codec9.encode(byteData, getter9.apply(decipherFunc));
                codec10.encode(byteData, getter10.apply(decipherFunc));
                codec11.encode(byteData, getter11.apply(decipherFunc));
            }
        };
    }
}
